package it.zgiovanni2003.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.zgiovanni2003.common.Database_Manager;

/**
 * Servlet implementation class DeleteCategoria
 */
public class DeleteCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoria() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String id=request.getParameter("id");
    		String query = "DELETE FROM categorie WHERE `categorie`.`categoria_id` = ?";

    		String[] params= {id};
    		int righe=db.toPost(query, params);
    		
    		if (righe>0) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione della categoria");
            }
	
	}

}
