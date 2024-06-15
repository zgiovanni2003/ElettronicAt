package it.zgiovanni2003.control;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.zgiovanni2003.model.CsrfTokenManager;
import it.zgiovanni2003.model.Database_Manager;

/**
 * Servlet implementation class AddProdotto
 */

public class AddProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Database_Manager db = new Database_Manager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String csrfTokenFromRequest = request.getParameter("csrfToken");
        String csrfTokenFromSession = CsrfTokenManager.getCsrfTokenFromSession(request);
        System.out.println(csrfTokenFromSession);

        if (csrfTokenFromRequest != null && csrfTokenFromRequest.equals(csrfTokenFromSession)) {
        	String nome_prodotto=request.getParameter("nome_prodotto");
        	String descrizione=request.getParameter("descrizione");
        	String prezzo=request.getParameter("prezzo");
        	String categoria_id=request.getParameter("categoria_id");
        	
        	String uploadDir =request.getServletContext().getRealPath("");

            // Ottieni il file caricato
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            String filePath = uploadDir + fileName;

            // Controlla se il file ha un'estensione .jpg
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                // Salva il file sul server
                filePart.write(filePath);
                //response.getWriter().println("Il file è stato caricato correttamente e ha un'estensione .jsp.");
                response.sendRedirect(request.getContextPath()+"/admin/gest-prodotti.jsp");
            } else {
                // Elimina il file se non ha un'estensione .jpg
                File fileToDelete = new File(filePath);
                if (fileToDelete.delete()) {
                    //response.getWriter().println("Il file non è stato caricato. Si prega di caricare un file con estensione .jsp.");
                } else {
                    //response.getWriter().println("Errore durante l'eliminazione del file. Si prega di contattare l'amministratore di sistema.");
                }
                //response.getWriter().println("Il file non è stato caricato. Si prega di caricare un file con estensione .jsp.");
            }
        	
        	
    		String query = "INSERT INTO `prodotti` (`prodotto_id`, `nome_prodotto`, `descrizione`, `prezzo`, `img`, `categoria_id`) "
    				+ "VALUES (NULL, ?, ?, ?, ?, ?)";

    		String[] params= {nome_prodotto,descrizione,prezzo,filePath,categoria_id};

    		db.toPost(query, params);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token CSRF non valido");
        }
	}

}
