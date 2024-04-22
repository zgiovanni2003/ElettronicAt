package it.zgiovanni2003.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String born_date= request.getParameter("born-date");
		String name= request.getParameter("name");
		String surname= request.getParameter("surname");
		Password_Manager pm = new Password_Manager();		
		String pswHash = null;
		try {
			pswHash = pm.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hash=pm.getKey();
		
		String query = "INSERT INTO `utente` (`name`, `surname`, `email`, `password`, `hash`, `born-date`)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		String[] params= {name,surname,email,pswHash,hash,born_date};
		
		db.toPost(query, params);
		
		response.sendRedirect("login.jsp");
		
		
	}

}
