package it.zgiovanni2003.control;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.zgiovanni2003.model.Database_Manager;

/**
 * Servlet implementation class DeleteProdotti
 */
@WebServlet("/DeleteProdotti")
public class DeleteProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProdotti() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String url_img = null;
		String query = "SELECT img FROM prodotti WHERE prodotti.prodotto_id = ?";
		String query2 = "DELETE FROM prodotti WHERE prodotti.prodotto_id = ?";
		String[] params= {id};
		ResultSet result = db.toGet(query, params);
		
		try {
			if(result.next()) url_img=result.getString("img");
			File file = new File(url_img);
			file.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int righe2=db.toPost(query2, params);
		
		if (righe2>0) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione del prodotto");
        }
	}


}
