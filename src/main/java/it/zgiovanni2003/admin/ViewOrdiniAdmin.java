package it.zgiovanni2003.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.zgiovanni2003.common.Database_Manager;

/**
 * Servlet implementation class ViewOrdini
 */

public class ViewOrdiniAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrdiniAdmin() {
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

		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        
        String email = (String) session.getAttribute("email");
        String query="SELECT `ordini`.`ordine_id`, `prodotti`.`nome_prodotto`, `ordini`.`data_ordine`, `ordini`.`prezzo_tot`, `ordini`.`quantità`, `utente`.`email`, `utente`.`name`, `utente`.`surname` "
        		+ "FROM `ordini` "
        		+ "LEFT JOIN `prodotti` ON `ordini`.`prodotto_id` = `prodotti`.`prodotto_id` "
        		+ "LEFT JOIN `utente` ON `ordini`.`email` = `utente`.`email` "
        		+ "WHERE `ordini`.`prodotto_id` = `prodotti`.`prodotto_id` AND `ordini`.`email` = `utente`.`email`";
        
        String[] params = {};
        ResultSet resultSet = db.toGet(query, params);

        JsonArray jsonArray = new JsonArray();
        try {
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("ordine_id", resultSet.getInt("ordine_id"));
                jsonObject.addProperty("nome_prodotto", resultSet.getString("nome_prodotto"));
                jsonObject.addProperty("data_ordine", resultSet.getString("data_ordine"));
                jsonObject.addProperty("prezzo_tot", resultSet.getDouble("prezzo_tot"));
                jsonObject.addProperty("quantity", resultSet.getInt("quantità"));
                jsonObject.addProperty("email", resultSet.getString("email"));
                jsonObject.addProperty("name", resultSet.getString("name"));
                jsonObject.addProperty("surname", resultSet.getString("surname"));
                jsonArray.add(jsonObject);
            }
            out.println(new Gson().toJson(jsonArray));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
           
	}



}
