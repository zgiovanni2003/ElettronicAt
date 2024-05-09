package it.zgiovanni2003.admin;

import it.zgiovanni2003.common.Database_Manager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCategoria
 */
public class AddCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoria() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
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
    				+ " VALUES ('', ?)";

    		String[] params= {nome_categoria};

    		db.toPost(query, params);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token CSRF non valido");
        }
	}

}
