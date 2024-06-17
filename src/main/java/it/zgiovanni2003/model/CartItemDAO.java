package it.zgiovanni2003.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class CartItemDAO {
    private Database_Manager db;

    public CartItemDAO() {
        db = new Database_Manager();
    }

    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }

    public void addToCart(HttpSession session, int productId, int quantity) {
        List<CartItem> cartItems = getCartItems(session);
        boolean found = false;
        for (CartItem item : cartItems) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            cartItems.add(new CartItem(productId, quantity));
        }
        session.setAttribute("cartItems", cartItems);
    }

    public void removeFromCart(HttpSession session, int productId) {
        List<CartItem> cartItems = getCartItems(session);
        cartItems.removeIf(item -> item.getProductId() == productId);
        session.setAttribute("cartItems", cartItems);
    }

    public ResultSet getProductDetails(int productId) throws SQLException {
        String query = "SELECT prodotti.prodotto_id, prodotti.nome_prodotto, prodotti.descrizione, prodotti.prezzo, prodotti.img, categorie.nome_categoria "
                     + "FROM prodotti LEFT JOIN categorie ON prodotti.categoria_id = categorie.categoria_id "
                     + "WHERE prodotti.prodotto_id = ?";
        String[] params = { String.valueOf(productId) };
        return db.toGet(query, params);
    }
}
