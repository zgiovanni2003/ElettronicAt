package it.zgiovanni2003.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.zgiovanni2003.model.Database_Manager;

/**
 * Servlet implementation class SelectCategoria
 */

public class SelectCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Database_Manager db = new Database_Manager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCategoria() {
    	super();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String query = "SELECT categoria_id, nome_categoria FROM categorie";
        String[] params = {};
        ResultSet resultSet = db.toGet(query, params);

        JsonArray jsonArray = new JsonArray();
        try {
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("categoria_id", resultSet.getInt("categoria_id"));
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
