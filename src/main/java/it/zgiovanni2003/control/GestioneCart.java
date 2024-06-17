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

import it.zgiovanni2003.model.CartItemDAO;
import it.zgiovanni2003.model.CartItem;


public class GestioneCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartItemDAO cartItemDAO;

    public GestioneCart() {
        super();
        cartItemDAO = new CartItemDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        response.setContentType("application/json");

        if ("view".equals(action)) {
            viewCart(session, response);
        } else if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            cartItemDAO.removeFromCart(session, productId);
            response.getWriter().write("{\"status\": \"success\"}");
        } else if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cartItemDAO.addToCart(session, productId, quantity);
            response.getWriter().write("{\"status\": \"success\"}");
        }
    }

    private void viewCart(HttpSession session, HttpServletResponse response) throws IOException {
        JsonArray jsonArray = new JsonArray();
        List<CartItem> cartItems = cartItemDAO.getCartItems(session);
        PrintWriter out = response.getWriter();

        if (cartItems != null) {
            for (CartItem item : cartItems) {
                try {
                    ResultSet resultSet = cartItemDAO.getProductDetails(item.getProductId());

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
                    e.printStackTrace();
                }
            }
        }

        out.println(new Gson().toJson(jsonArray));
        out.close();
    }
}
