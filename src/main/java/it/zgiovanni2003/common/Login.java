package it.zgiovanni2003.common;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);  
    Password_Manager pm = new Password_Manager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
        // TODO Auto-generated constructor stub
    }

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		String query = "SELECT `hash` FROM `utente` WHERE `email`= ?";

		String[] params= {email};

		ResultSet resultSet = db.toGet(query, params);
		String hash=null;
		try {
			if (resultSet.next()) {
			     hash = resultSet.getString("hash");
			     String pswHash = pm.encrypt(password, hash);
			     System.out.println(pswHash);
			     String temp = "SELECT `email`  FROM `utente` WHERE `email`= ? AND `password`= ?";
			     String[] param= {email,pswHash};

			     resultSet = db.toGet(temp, param);
					
			     if(resultSet.next()) {
			    	 HttpSession oldSession= request.getSession(false);
						
			    	 if(oldSession != null) oldSession.invalidate();
						
						HttpSession session= request.getSession();
						session.setAttribute("email", email);
						
						response.sendRedirect("index.jsp");
			     }
			}else {
				response.sendRedirect("login.jsp");
			}

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
