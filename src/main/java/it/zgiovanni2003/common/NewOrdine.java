package it.zgiovanni2003.common;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewOrdine
 */
public class NewOrdine extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String driver = "com.mysql.cj.jdbc.Driver";
    String URL_mioDB = "jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver); 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrdine() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        int righe=0;
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                String id = Integer.toString(item.getProductId());
                String qt = Integer.toString(item.getQuantity());

                String query = "SELECT prodotti.prezzo FROM prodotti WHERE prodotti.prodotto_id = ?";
                String[] params = {id};
                ResultSet resultSet = db.toGet(query, params);
                double prezzo = 0.0;

                try {
                    if (resultSet.next()) {
                        prezzo = resultSet.getDouble("prezzo");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String prezzo_f = Double.toString(prezzo * item.getQuantity());

                String query2 = "INSERT INTO ordini (ordine_id, email, prodotto_id, data_ordine, prezzo_tot, quantità) "
                        + "VALUES (NULL, ?, ?, DEFAULT, ?, ?)";
                String[] params2 = {email, id, prezzo_f, qt};

                righe = db.toPost(query2, params2);

                
            }
        }
        if (righe > 0) {
            System.out.println("Ordine inserito con successo per l'email: " + email);
            if (cartItems != null) {
                // Rimuovi l'elemento dal carrello
                for (int i = 0; i < cartItems.size(); i++)cartItems.remove(i); 
                session.setAttribute("cartItems", cartItems);

            }
            response.sendRedirect("carrello.jsp");
        } else {
            System.out.println("Errore nell'inserimento dell'ordine per l'email: " + email);
            response.sendRedirect("carrello.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Reindirizzare al doPost per gestire l'inserimento tramite GET (non raccomandato in produzione)
        doPost(request, response);
    }
}
