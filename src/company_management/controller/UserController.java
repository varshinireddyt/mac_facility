package company_management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company_management.data.UserDAO;
import company_management.model.*;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private void getUserParam (HttpServletRequest request, User user) {
		user.setUser("", request.getParameter("username"), request.getParameter("password"), 
				request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("email"), request.getParameter("role"), request.getParameter("revoke_status"));  
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		//list user by manager
		if (action.equalsIgnoreCase("managerUserList")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			userInDB=UserDAO.managerUserList();
			session.setAttribute("USERS", userInDB);
			getServletContext().getRequestDispatcher("/managerUserList.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user= new User();
		int selectedUserIndex;
		if (action.equalsIgnoreCase("managerSearchUser")) {
			String first=request.getParameter("firstName");
			String last=request.getParameter("lastName");
			user.setUser("", "", "", first, last, "", "","");
			ArrayList<User> userInDB = new ArrayList<User>();
			if(!first.equals(""))
				userInDB=UserDAO.managerSearchUserfirst(first);
			else
				userInDB=UserDAO.managerSearchUserlast(last);
			session.setAttribute("USERS", userInDB);
			session.removeAttribute("user");
			System.out.print("GfG1");
			url="/managerSearchResults.jsp";			
		}
		else if (action.equalsIgnoreCase("listSpecificUser")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			User selectedUser = new User();
			userInDB=UserDAO.managerSearchUserId(request.getParameter("id"));
			selectedUser.setUser(userInDB.get(0).getUserId(), userInDB.get(0).getUserName(), userInDB.get(0).getPassword(),
					userInDB.get(0).getFirstName(), userInDB.get(0).getLastName(), userInDB.get(0).getEmail(), userInDB.get(0).getRole(),userInDB.get(0).getRevoke_status());
			session.setAttribute("USERS", selectedUser);
			url="/managerViewSpecificUser.jsp";					
		}
		else if (action.equals("registration")) {
			if (request.getParameter("registrationButton")!=null) {  
				getUserParam(request, user);
				session.setAttribute("user", user);
				System.out.println(user.getRole());
				UserDAO.insertUser(user);			
				request.setAttribute("sucMessage", "Successfully created new account.");
				url = "/index.jsp"; 
				}
		}
		else if (action.equalsIgnoreCase("viewMyProfile")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			User selectedUser = new User();
			userInDB=UserDAO.searchUser(request.getParameter("id"));
			selectedUser.setUser(userInDB.get(0).getUserId(),userInDB.get(0).getUserName(), userInDB.get(0).getPassword(),
					userInDB.get(0).getFirstName(), userInDB.get(0).getLastName(), userInDB.get(0).getEmail(), userInDB.get(0).getRole(),userInDB.get(0).getRevoke_status());
			session.setAttribute("USERS", selectedUser);
			url="/viewMyProfile.jsp";					
		}
		
		else if (action.equalsIgnoreCase("updateMyProfile")) {
			if (request.getParameter("updateProfileButton")!=null) {  
				getUserParam(request, user);
				user.setUserId(request.getParameter("userId"));
				UserDAO.updateMyProfile(user);			
				request.setAttribute("sucMessage", "Successfully update profile.");
				url = "/UserController?action=viewMyProfile&id=" + user.getUserId(); 
				}				
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
	
}