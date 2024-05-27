package it.zgiovanni2003.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.zgiovanni2003.common.Database_Manager;

/**
 * Servlet implementation class SelectProdotti
 */

public class SelectProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProdotti() {
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
        String query = "SELECT prodotti.prodotto_id, prodotti.nome_prodotto, prodotti.descrizione, prodotti.prezzo, prodotti.img, categorie.nome_categoria "
        		+ "FROM prodotti LEFT JOIN categorie ON prodotti.categoria_id = categorie.categoria_id";
        String[] params = {};
        ResultSet resultSet = db.toGet(query, params);

        JsonArray jsonArray = new JsonArray();
        try {
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("prodotto_id", resultSet.getInt("prodotto_id"));
                jsonObject.addProperty("nome_prodotto", resultSet.getString("nome_prodotto"));
                jsonObject.addProperty("descrizione", resultSet.getString("descrizione"));
                jsonObject.addProperty("prezzo", resultSet.getDouble("prezzo"));
                jsonObject.addProperty("img", resultSet.getString("img"));
                jsonObject.addProperty("nome_categoria", resultSet.getString("nome_categoria"));
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
