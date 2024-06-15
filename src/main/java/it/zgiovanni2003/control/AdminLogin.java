package it.zgiovanni2003.control;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.zgiovanni2003.model.Database_Manager;
import it.zgiovanni2003.model.Password_Manager;

/**
 * Servlet implementation class AdminLogin
 */
//@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver="com.mysql.cj.jdbc.Driver";
    String URL_mioDB="jdbc:mysql://localhost:3306/e-commerce";
    Database_Manager db = new Database_Manager(URL_mioDB, driver);  
    Password_Manager pm = new Password_Manager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        db.connectDriver();
        db.connect_DB("root", "");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		String query = "SELECT `hash` FROM `admin` WHERE `email`= ?";

		String[] params= {email};

		ResultSet resultSet = db.toGet(query, params);
		String hash=null;
		try {
			if (resultSet.next()) {
			     hash = resultSet.getString("hash");
			     String pswHash = pm.encrypt(password, hash);
			     //System.out.println(pswHash);
			     String temp = "SELECT `email`  FROM `admin` WHERE `email`= ? AND `password`= ?";
			     String[] param= {email,pswHash};

			     resultSet = db.toGet(temp, param);
					
			     if(resultSet.next()) {
			    	 HttpSession oldSession= request.getSession(false);
						
			    	 if(oldSession != null) oldSession.invalidate();
						
						HttpSession session= request.getSession();
						session.setAttribute("admin-email", email);
						
						response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
			     }
			     else {
			    	 response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
			     }
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
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
