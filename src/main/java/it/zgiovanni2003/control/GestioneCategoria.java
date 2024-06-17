package it.zgiovanni2003.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.zgiovanni2003.model.CategoriaDAO;
import it.zgiovanni2003.model.Categoria;
import it.zgiovanni2003.model.CsrfTokenManager;

/**
 * Servlet implementation class GestioneCategoria
 */
public class GestioneCategoria extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO;

    public GestioneCategoria() {
        super();
        this.categoriaDAO = new CategoriaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String csrfTokenFromRequest = request.getParameter("csrfToken");
        String csrfTokenFromSession = CsrfTokenManager.getCsrfTokenFromSession(request);

        if (csrfTokenFromRequest != null && csrfTokenFromRequest.equals(csrfTokenFromSession)) {
            try {
                if ("add".equalsIgnoreCase(action)) {
                    aggiungiCategoria(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Azione non valida");
                }
            } catch (SQLException e) {
                throw new ServletException("Errore durante l'operazione", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token CSRF non valido");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("delete".equalsIgnoreCase(action)) {
                eliminaCategoria(request, response);
            } else if ("select".equalsIgnoreCase(action)) {
                selezionaCategorie(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Azione non valida");
            }
        } catch (SQLException e) {
            throw new ServletException("Errore durante l'operazione", e);
        }
    }

    private void aggiungiCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nomeCategoria = request.getParameter("nome_categoria");
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(nomeCategoria);
        categoriaDAO.aggiungiCategoria(categoria);
        response.sendRedirect(request.getContextPath() + "/admin/gest-categoria.jsp");
    }

    private void eliminaCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int righe = categoriaDAO.eliminaCategoria(id);

        if (righe > 0) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione della categoria");
        }
    }

    private void selezionaCategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<Categoria> categorie = categoriaDAO.selezionaCategorie();
        
        JsonArray jsonArray = new JsonArray();
        for (Categoria categoria : categorie) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("categoria_id", categoria.getId());
            jsonObject.addProperty("nome_categoria", categoria.getNomeCategoria());
            jsonArray.add(jsonObject);
        }
        out.println(new Gson().toJson(jsonArray));
        out.close();
    }
}
