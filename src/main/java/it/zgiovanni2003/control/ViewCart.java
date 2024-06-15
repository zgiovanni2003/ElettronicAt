package it.zgiovanni2003.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.zgiovanni2003.model.CartItem;
import it.zgiovanni2003.model.Database_Manager;

/**
 * Servlet implementation class ViewCart
 */
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database_Manager db = new Database_Manager(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCart() {
        super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		JsonArray jsonArray = new JsonArray();

        HttpSession session = request.getSession();
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        PrintWriter out = response.getWriter();
        if(cartItems!=null) {
	        for (CartItem item : cartItems) {
	        	String query = "SELECT prodotti.prodotto_id, prodotti.nome_prodotto, prodotti.descrizione, prodotti.prezzo, prodotti.img, categorie.nome_categoria "
	            		+ "FROM prodotti LEFT JOIN categorie ON prodotti.categoria_id = categorie.categoria_id "
	            		+ "WHERE prodotti.prodotto_id = ?";
	        	String id= Integer.toString(item.getProductId());
	            String[] params = {id};
	            ResultSet resultSet = db.toGet(query, params);
	            try {
					while (resultSet.next()) {
					    JsonObject jsonObject = new JsonObject();
					    jsonObject.addProperty("prodotto_id", resultSet.getInt("prodotto_id"));
					    jsonObject.addProperty("nome_prodotto", resultSet.getString("nome_prodotto"));
					    jsonObject.addProperty("descrizione", resultSet.getString("descrizione"));
					    jsonObject.addProperty("prezzo", resultSet.getDouble("prezzo"));
					    jsonObject.addProperty("img", resultSet.getString("img"));
					    jsonObject.addProperty("nome_categoria", resultSet.getString("nome_categoria"));
					    jsonObject.addProperty("quantity", item.getQuantity());
					    jsonArray.add(jsonObject);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        out.println(new Gson().toJson(jsonArray));
	        out.close();
        }
           
	}

}
