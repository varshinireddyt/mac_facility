package company_management.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(name="logout",urlPatterns={"/LogoutController"})
public class LogoutController extends HttpServlet
{
	 private static final long serialVersionUID = 1L;
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	 {
		 HttpSession session = request.getSession(false); //Fetch session object
		 
		 if(session!=null) //If session is not null
		 {
			 session.invalidate(); //removes all session attributes bound to the session
			 request.setAttribute("errMessage", "You are logged out");
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			 requestDispatcher.forward(request, response);
		 }
	 }
}