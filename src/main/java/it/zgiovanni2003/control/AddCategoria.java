package it.zgiovanni2003.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.zgiovanni2003.model.CsrfTokenManager;
import it.zgiovanni2003.model.Database_Manager;

/**
 * Servlet implementation class AddCategoria
 */
public class AddCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database_Manager db = new Database_Manager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoria() {
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

        if (csrfTokenFromRequest != null && csrfTokenFromRequest.equals(csrfTokenFromSession)) {
        	String nome_categoria=request.getParameter("nome_categoria");
    		String query = "INSERT INTO `categorie` (`categoria_id`, `nome_categoria`)"
    				+ " VALUES (NULL, ?)";

    		String[] params= {nome_categoria};

    		db.toPost(query, params);
    		response.sendRedirect(request.getContextPath()+"/admin/gest-categoria.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token CSRF non valido");
        }
	}

}
