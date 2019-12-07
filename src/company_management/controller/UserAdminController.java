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
import company_management.model.User;

@WebServlet("/UserAdminController")
public class UserAdminController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		//list user by manager
				if (action.equalsIgnoreCase("adminUserList")) {
					ArrayList<User> userInDB = new ArrayList<User>();
					userInDB=UserDAO.adminUserList();
					session.setAttribute("USERS", userInDB);
					getServletContext().getRequestDispatcher("/adminUserList.jsp").forward(request, response);
				}
				else // redirect all other gets to post
					doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user= new User();
	//	int selectedUserIndex;
		if (action.equalsIgnoreCase("adminSearchUser")) {
			String first=request.getParameter("firstName");
			String last=request.getParameter("lastName");
			user.setUser("", "", "", first, last, "", "","");
			ArrayList<User> userInDB = new ArrayList<User>();
			if(!first.equals(""))
				userInDB=UserDAO.adminSearchUserfirst(first);
			else
				userInDB=UserDAO.adminSearchUserlast(last);
			session.setAttribute("USERS", userInDB);
			session.removeAttribute("user");
//			System.out.print("GfG1");
			url="/adminUserList.jsp";			
		}
		else if (action.equalsIgnoreCase("listSpecificUser")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			User selectedUser = new User();
			userInDB=UserDAO.adminSearchUserId(request.getParameter("id"));
			selectedUser.setUser(userInDB.get(0).getUserId(), userInDB.get(0).getUserName(), userInDB.get(0).getPassword(),
					userInDB.get(0).getFirstName(), userInDB.get(0).getLastName(), userInDB.get(0).getEmail(), userInDB.get(0).getRole(),userInDB.get(0).getRevoke_status());
			session.setAttribute("USERS", selectedUser);
			url="/adminViewSpecificUser.jsp";					
		}
		else if(action.equalsIgnoreCase("modifyUser")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			String Id=request.getParameter("id");
			String role=request.getParameter("Role");
			User selectedUser = new User();
			userInDB=UserDAO.modifyUser(Id, role);
			selectedUser.setUser(userInDB.get(0).getUserId(), userInDB.get(0).getUserName(), userInDB.get(0).getPassword(),
					userInDB.get(0).getFirstName(), userInDB.get(0).getLastName(), userInDB.get(0).getEmail(), userInDB.get(0).getRole(),userInDB.get(0).getRevoke_status());
			session.setAttribute("USERS", selectedUser);
			request.setAttribute("suhMessage", "Modified Successfully.");
			url="/adminViewSpecificUser.jsp";				
		}
		else if(action.equalsIgnoreCase("revokeUser")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			String Id=request.getParameter("id");
			String Revoke=request.getParameter("Revoke");
			User selectedUser = new User();
			userInDB=UserDAO.revokeUser(Id, Revoke);
			selectedUser.setUser(userInDB.get(0).getUserId(), userInDB.get(0).getUserName(), userInDB.get(0).getPassword(),
					userInDB.get(0).getFirstName(), userInDB.get(0).getLastName(), userInDB.get(0).getEmail(), userInDB.get(0).getRole(),userInDB.get(0).getRevoke_status());
			session.setAttribute("USERS", selectedUser);
			request.setAttribute("erMessage", "Rovoke Status Changed Successfully.");
			url="/adminViewSpecificUser.jsp";				
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
}
