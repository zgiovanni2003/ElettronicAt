package it.zgiovanni2003;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		if(email.equals("pippo") && password.equals("pluto")) {
			HttpSession oldSession= request.getSession(false);
			
			if(oldSession != null) oldSession.invalidate();
			
			HttpSession session= request.getSession();
			session.setAttribute("email", email);
			
			response.sendRedirect("index.jsp");
			
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}
