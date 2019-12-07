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
import company_management.data.ReservationDAO;
import company_management.data.UserDAO;
import company_management.data.UserFacilityDAO;
import company_management.model.*;

@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	private void getReservationParam (HttpServletRequest request, Reservation reservation) {
		reservation.setReservation("",request.getParameter("iduser"),request.getParameter("idfacility"),request.getParameter("start_date"),request.getParameter("start_time"),request.getParameter("violation_type"));  
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("errorMsgs");
//		List reservations
		if (action.equalsIgnoreCase("listReservation")) {
			System.out.println("00000000000000000000000000000");
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			reservationInDB=ReservationDAO.listSpecificReservations(request.getParameter("iduser"));
			session.setAttribute("RESERVATIONS", reservationInDB);				
			getServletContext().getRequestDispatcher("/reservationList.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("listAllReservation")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			reservationInDB=ReservationDAO.listReservations();
			session.setAttribute("RESERVATIONS", reservationInDB);
			getServletContext().getRequestDispatcher("/ListAllReservation.jsp").forward(request, response);
			
		}
		else // redirect all other gets to post
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		Reservation reservation = new Reservation();
		int selectedReservationIndex;
		session.removeAttribute("errorMsgs");

		if (action.equalsIgnoreCase("payReservation") ) {  
			ArrayList<UserFacility> facilityInDB=new ArrayList<UserFacility>();
			facilityInDB=UserFacilityDAO.searchFacility(request.getParameter("idfacility"));
			session.setAttribute("FACILITY", facilityInDB.get(0));
			ArrayList<User> userInDB = new ArrayList<User>();
			userInDB=UserDAO.searchUser(request.getParameter("iduser"));

			session.setAttribute("USER", userInDB.get(0));

			session.setAttribute("DATE", request.getParameter("start_date"));
			session.setAttribute("TIME", request.getParameter("start_time"));
			url="/payment.jsp";
		}
		
		else if (action.equalsIgnoreCase("saveReservation") ) {
			String iduser = request.getParameter("iduser");
			String idfacility = request.getParameter("idfacility");
			String start_date=request.getParameter("start_date");
			String start_time=request.getParameter("start_time");
			reservation.setIdfacility(idfacility);
			reservation.setIduser(iduser);
			reservation.setStart_date(start_date);
			reservation.setStart_time(start_time);
			ReservationDAO.insertReservation(reservation);
			session.setAttribute("RESERVATION", reservation);
			
			url="/ReservationController?action=listReservation&iduser="+iduser; 
		}
		
		else if (action.equalsIgnoreCase("modifyReservation")) {
			ArrayList<Reservation> reservationInDB=new ArrayList<Reservation>();
			String Id=request.getParameter("idreservation");
			String date=request.getParameter("start_date");
			String time=request.getParameter("start_time");
			Reservation selectedReservation = new Reservation();
			reservationInDB=ReservationDAO.modifyReservation(Id, date, time);
			selectedReservation.setReservation(	reservationInDB.get(0).getIdreservation(), reservationInDB.get(0).getIduser(), reservationInDB.get(0).getIdfacility(),
					reservationInDB.get(0).getStart_date(), reservationInDB.get(0).getStart_time(), reservationInDB.get(0).getViolation_type());
			//session.setAttribute("FACILITIES", selectedReservation);
			request.setAttribute("suchMessage", "Modified Successfully.");
			url = "/ReservationController?action=listReservation&iduser="+reservationInDB.get(0).getIduser(); 			
			/*
			if (request.getParameter("modifyReservationButton")!=null) {  
				getReservationParam(request, reservation);
				reservation.setIdreservation(request.getParameter("idreservation"));
				ReservationDAO.modifyReservation(reservation);			
				request.setAttribute("sucMessage", "Successfully modified Reservation");
				url = "/ReservationController?action=listSpecificReservation&idreservation=" + reservation.getIdreservation(); 
				}
			*/
		}
		else if (action.equalsIgnoreCase("reportReservation")) {
			ArrayList<Reservation> reservationInDB=new ArrayList<Reservation>();
			String Id=request.getParameter("id");
			String violation=request.getParameter("violation_type");
			Reservation selectedReservation = new Reservation();
			reservationInDB=ReservationDAO.reportReservation(Id, violation);
			selectedReservation.setReservation(	reservationInDB.get(0).getIdreservation(), reservationInDB.get(0).getIduser(), reservationInDB.get(0).getIdfacility(),
					reservationInDB.get(0).getStart_date(), reservationInDB.get(0).getStart_time(), reservationInDB.get(0).getViolation_type());
			session.setAttribute("RESERVATION", selectedReservation);
			request.setAttribute("chMessage","Reported Successfully.");
			url="/managerViewSpecificReservation.jsp";
			
		}
		
		else if (action.equalsIgnoreCase("cancelReservation")) {
			getReservationParam(request, reservation);
			reservation.setIdreservation(request.getParameter("idreservation"));
			String iduser = request.getParameter("iduser");
			ReservationDAO.cancelReservation(reservation);			
			request.setAttribute("sucMessage", "Successfully cancelled Reservation");

			url = "/ReservationController?action=listReservation&iduser="+iduser; 
				
		}
		
		else if (action.equalsIgnoreCase("listReservation")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			reservationInDB=ReservationDAO.listSpecificReservations(request.getParameter("iduser"));
			session.setAttribute("RESERVATIONS", reservationInDB);				
			url="/reservationList.jsp";
		}
		else if (action.equalsIgnoreCase("listMyViolations")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			reservationInDB=ReservationDAO.listMyViolations(request.getParameter("iduser"));
			session.setAttribute("RESERVATIONS", reservationInDB);
			url="/listMyViolations.jsp";
			
		}
		else if (action.equalsIgnoreCase("ViewMYSpecificViolation")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			ArrayList<UserFacility> facilityInDB = new ArrayList<UserFacility>();
			Reservation selectedReservation = new Reservation();
			UserFacility selectedFacility = new UserFacility();
			reservationInDB=ReservationDAO.searchReservation(request.getParameter("idreservation"));
			facilityInDB=UserFacilityDAO.searchFacility(reservationInDB.get(0).getIdfacility());
			
			selectedReservation.setReservation(	reservationInDB.get(0).getIdreservation(), reservationInDB.get(0).getIduser(), reservationInDB.get(0).getIdfacility(),
							reservationInDB.get(0).getStart_date(), reservationInDB.get(0).getStart_time(), reservationInDB.get(0).getViolation_type());
			selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(),
							facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(), facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
		
			session.setAttribute("RESERVATION", selectedReservation);
			session.setAttribute("FACILITY", selectedFacility);
			url="/ViewMySpecificViolation.jsp";
		}
		else if (action.equalsIgnoreCase("managerDeleteReservation")) {
			getReservationParam(request, reservation);
			reservation.setIdreservation(request.getParameter("id"));
			ReservationDAO.cancelReservation(reservation);			
			request.setAttribute("eMessage", "Successfully deleted Reservation");
			url = "/ReservationController?action=listAllReservation"; 
		}
		else if (action.equalsIgnoreCase("listAllReservation")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			reservationInDB=ReservationDAO.listReservations();
			session.setAttribute("RESERVATIONS", reservationInDB);
			url="/ListAllReservation.jsp";
		}
		
		else if (action.equalsIgnoreCase("managerViewSpecificReservation")) {
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			ArrayList<UserFacility> facilityInDB = new ArrayList<UserFacility>();
			Reservation selectedReservation = new Reservation();
			UserFacility selectedFacility = new UserFacility();
			reservationInDB=ReservationDAO.searchReservation(request.getParameter("idreservation"));
			facilityInDB=UserFacilityDAO.searchFacility(reservationInDB.get(0).getIdfacility());
			
			selectedReservation.setReservation(	reservationInDB.get(0).getIdreservation(), reservationInDB.get(0).getIduser(), reservationInDB.get(0).getIdfacility(),
							reservationInDB.get(0).getStart_date(), reservationInDB.get(0).getStart_time(), reservationInDB.get(0).getViolation_type());
			selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(),
							facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(), facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
		
			session.setAttribute("RESERVATION", selectedReservation);
			session.setAttribute("FACILITY", selectedFacility);
			url="/managerViewSpecificReservation.jsp";
		}

		else { //action=listSpecificReservation
			ArrayList<Reservation> reservationInDB = new ArrayList<Reservation>();
			ArrayList<UserFacility> facilityInDB = new ArrayList<UserFacility>();
			Reservation selectedReservation = new Reservation();
			UserFacility selectedFacility = new UserFacility();
			reservationInDB=ReservationDAO.searchReservation(request.getParameter("idreservation"));
			facilityInDB=UserFacilityDAO.searchFacility(reservationInDB.get(0).getIdfacility());
			
			selectedReservation.setReservation(	reservationInDB.get(0).getIdreservation(), reservationInDB.get(0).getIduser(), reservationInDB.get(0).getIdfacility(),
							reservationInDB.get(0).getStart_date(), reservationInDB.get(0).getStart_time(), reservationInDB.get(0).getViolation_type());
			selectedFacility.setFacility(facilityInDB.get(0).getIdfacility(), facilityInDB.get(0).getFacility_type(), facilityInDB.get(0).getFacility_name(),
							facilityInDB.get(0).getInterval(), facilityInDB.get(0).getDuration(), facilityInDB.get(0).getVenue(), facilityInDB.get(0).getDeposit(), facilityInDB.get(0).getAvailability(), facilityInDB.get(0).getDamage_status());
		
			session.setAttribute("RESERVATION", selectedReservation);
			session.setAttribute("FACILITY", selectedFacility);
			url="/reservationDetail.jsp";					
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}