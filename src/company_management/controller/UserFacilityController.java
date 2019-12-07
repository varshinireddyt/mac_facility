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
import company_management.data.UserFacilityDAO;
import company_management.model.*;

@WebServlet("/UserFacilityController")
public class UserFacilityController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	private void getFacilityParam (HttpServletRequest request, UserFacility facility) {
		facility.setFacility(request.getParameter("idfacility"),request.getParameter("facility_type"),request.getParameter("facility_name"),request.getParameter("interval"),request.getParameter("duration"),request.getParameter("venue"),request.getParameter("deposit"), request.getParameter("availability"),request.getParameter("damage_status"));  
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");
//		List companies
		if (action.equalsIgnoreCase("listFacility")) {
			ArrayList<User> userInDB = new ArrayList<User>();
			userInDB=UserDAO.searchUser(request.getParameter("iduser"));
			session.setAttribute("USER", userInDB.get(0));			
			getServletContext().getRequestDispatcher("/searchFacility.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		UserFacility facility = new UserFacility();
		int selectedFacilityIndex;

		  if (action.equalsIgnoreCase("searchFacility") ) {
			String facilitytype = request.getParameter("facility_type");
			String startdate = request.getParameter("start_date");  
			String starttime = request.getParameter("start_time");
			//facility.setFacility("", facilitytype, "");
			ArrayList<UserFacility> facilityInDB = new ArrayList<UserFacility>();
			facilityInDB=UserFacilityDAO.searchFacilities(facilitytype);
			session.setAttribute("FACILITIES", facilityInDB);
			ArrayList<User> userInDB = new ArrayList<User>();
			userInDB=UserDAO.searchUser(request.getParameter("iduser"));
			session.setAttribute("USER", userInDB.get(0));	
			session.setAttribute("DATE", startdate);
			session.setAttribute("TIME", starttime);
			url="/facilityList.jsp";
		}
		else { //action=listSpecificFacility
			ArrayList<UserFacility> facilityInDB = new ArrayList<UserFacility>();
			UserFacility selectedFacility = new UserFacility();
					facilityInDB=UserFacilityDAO.searchFacility(request.getParameter("id"));
					selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(), facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(), facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
					session.setAttribute("FACILITIES", selectedFacility);
					url="/ListSpecificFacility.jsp";					
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}