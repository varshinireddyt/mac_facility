package company_management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import company_management.data.FacilityDAO;
import company_management.model.Facility;


@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		//list facility by
		if (action.equalsIgnoreCase("managerFacilityList")) {
			ArrayList<Facility> facilityInDB=new ArrayList<Facility>();
			facilityInDB=FacilityDAO.listFacilities();
			session.setAttribute("FACILITIES",facilityInDB);
			getServletContext().getRequestDispatcher("/listFacility.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		Facility facility=new Facility();
//		int selectedFacilityIndex;
		if (action.equalsIgnoreCase("managerSearchFacility")) {
			String type=request.getParameter("facilityType");
			String name=request.getParameter("facilityName");
			facility.setFacility("", type, name, "", "", "", "","","");
			ArrayList<Facility> facilityInDB=new ArrayList<Facility>();
			if(!type.equals(""))
				facilityInDB=FacilityDAO.searchFacilitiestype(type);
			else
				facilityInDB=FacilityDAO.searchFacilities(name);
			session.setAttribute("FACILITIES",facilityInDB);
			session.removeAttribute("facility");
//			System.out.println(session.getAttribute("FACILITIES"));
			url="/listFacility.jsp";
		}
		else if (action.equalsIgnoreCase("listSpecificFacility")) {
			ArrayList<Facility> facilityInDB=new ArrayList<Facility>();
			Facility selectedFacility = new Facility();
			facilityInDB=FacilityDAO.searchFacility(request.getParameter("id"));
			selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(), facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(),  facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
			session.setAttribute("FACILITIES", selectedFacility);
			url="/managerViewSpecificFacility.jsp";
					
		}
		else if(action.equalsIgnoreCase("modifyFacility")) {
			ArrayList<Facility> facilityInDB=new ArrayList<Facility>();
			String Id=request.getParameter("id");
		//	System.out.println(Id);
			String damage=request.getParameter("damage_status");
		//	System.out.println(damage);
			String avai=request.getParameter("availability");
		//	System.out.println(avai);
			Facility selectedFacility = new Facility();
			facilityInDB=FacilityDAO.modifyFacility(Id, damage, avai);
			selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(), facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(),  facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
			session.setAttribute("FACILITIES", selectedFacility);
			request.setAttribute("suchMessage", "Modified Successfully.");
			url="/managerViewSpecificFacility.jsp";		
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

}
