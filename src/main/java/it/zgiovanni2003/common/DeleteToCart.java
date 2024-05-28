package it.zgiovanni2003.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteToCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

        if (cartItems != null) {
            // Rimuovi l'elemento dal carrello
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getProductId() == productId) {
                    cartItems.remove(i);
                    break;
                }
            }
            session.setAttribute("cartItems", cartItems);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\"}");
        }
    }
}
