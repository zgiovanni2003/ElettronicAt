package it.zgiovanni2003.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.zgiovanni2003.model.ProdottiDAO;
import it.zgiovanni2003.model.CsrfTokenManager;
import it.zgiovanni2003.model.Prodotto;


@MultipartConfig
public class GestioneProdotti extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProdottiDAO prodottiDAO;

    public GestioneProdotti() {
        super();
        prodottiDAO = new ProdottiDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addProdotto(request, response);
        }else if ("update".equals(action)) {
            handleUpdateProdotto(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("select".equals(action)) {
            handleSelectProdotti(request, response);
        }else if ("selectById".equals(action)) {
            handleSelectProdottoById(request, response);
        }else if ("delete".equals(action)) {
            handleDeleteProdotto(request, response);
        }
    }

    private void addProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String csrfTokenFromRequest = request.getParameter("csrfToken");
        String csrfTokenFromSession = CsrfTokenManager.getCsrfTokenFromSession(request);

        if (csrfTokenFromRequest != null && csrfTokenFromRequest.equals(csrfTokenFromSession)) {
            String nome_prodotto = request.getParameter("nome_prodotto");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String categoria_id = request.getParameter("categoria_id");

            String uploadDir = request.getServletContext().getRealPath("images/load/");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            String filePath = uploadDir + fileName;

            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                filePart.write(filePath);
                Prodotto prodotto = new Prodotto(0, nome_prodotto, descrizione, prezzo, fileName, categoria_id);
                prodottiDAO.addProdotto(prodotto);
                response.sendRedirect(request.getContextPath() + "/admin/gest-prodotti.jsp");
            } else {
                File fileToDelete = new File(filePath);
                fileToDelete.delete();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File extension not allowed. Only .jpg, .jpeg, .png are allowed.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token CSRF non valido");
        }
    }
    
    private void handleUpdateProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prodottoId = request.getParameter("prodotto_id");
        String nome_prodotto = request.getParameter("nome_prodotto");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));

        Prodotto prodotto = new Prodotto(Integer.parseInt(prodottoId), nome_prodotto, descrizione, prezzo, null, null);
        int rowsUpdated = prodottiDAO.updateProdotto(prodotto);

        if (rowsUpdated > 0) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(request.getContextPath() + "/admin/gest-prodotti.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del prodotto");
        }
    }

    private void handleDeleteProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String url_img = prodottiDAO.getProdottoImg(id);
        if (url_img != null) {
            File file = new File(url_img);
            file.delete();
        }

        int righe = prodottiDAO.deleteProdotto(id);

        if (righe > 0) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione del prodotto");
        }
    }

    private void handleSelectProdotti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<Prodotto> prodotti = prodottiDAO.getProdotti();

        JsonArray jsonArray = new JsonArray();
        for (Prodotto prodotto : prodotti) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("prodotto_id", prodotto.getProdottoId());
            jsonObject.addProperty("nome_prodotto", prodotto.getNomeProdotto());
            jsonObject.addProperty("descrizione", prodotto.getDescrizione());
            jsonObject.addProperty("prezzo", prodotto.getPrezzo());
            jsonObject.addProperty("img", prodotto.getImg());
            jsonObject.addProperty("nome_categoria", prodotto.getNomeCategoria());
            jsonArray.add(jsonObject);
        }
        out.println(new Gson().toJson(jsonArray));
        out.close();
    }
    
    private void handleSelectProdottoById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Prodotto prodotto = prodottiDAO.getProdottoById(id);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        if (prodotto != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("prodotto_id", prodotto.getProdottoId());
            jsonObject.addProperty("nome_prodotto", prodotto.getNomeProdotto());
            jsonObject.addProperty("descrizione", prodotto.getDescrizione());
            jsonObject.addProperty("prezzo", prodotto.getPrezzo());
            jsonObject.addProperty("img", prodotto.getImg());
            jsonObject.addProperty("nome_categoria", prodotto.getNomeCategoria());

            out.println(gson.toJson(jsonObject));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        out.close();
    }

}
