package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import company_management.util.SQLConnection;
import company_management.model.Facility;
import company_management.model.Reservation;
import company_management.model.User;
public class ReservationDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Reservation> ReturnMatchingReservationsList (String queryString) {
		ArrayList<Reservation> reservationListInDB = new ArrayList<Reservation>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  

		try {
			stmt = conn.createStatement();
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				Reservation reservation = new Reservation(); 
				reservation.setIdreservation(reservationList.getString("idreservation"));
				reservation.setIduser(reservationList.getString("iduser"));
				reservation.setIdfacility(reservationList.getString("idfacility"));
				reservation.setStart_date(reservationList.getString("start_date"));
				reservation.setStart_time(reservationList.getString("start_time"));
				reservation.setViolation_type(reservationList.getString("violation_type"));
				reservationListInDB.add(reservation);	
			}
		} catch (SQLException e) {}
		return reservationListInDB;
	}
	
	private static void StoreListinDB (Reservation reservation,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertReservation = queryString + " VALUES ('"  
				    + reservation.getIduser()  + "','"
					+ reservation.getIdfacility() + "','"
					+ reservation.getStart_date()  + "','"
					+ reservation.getStart_time()  + "')";
			stmt.executeUpdate(insertReservation);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	
	public static ArrayList<Reservation> modifyReservation(String Id,String start_date,String start_time){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		String queryString="UPDATE RESERVATION SET start_date='"+start_date+"',start_time='"+start_time+"'WHERE idreservation='"+Id+"'";		
		conn = SQLConnection.getDBConnection(); 
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();					 
		} catch (SQLException e) {}   
		return ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDRESERVATION = '"+Id+"' ORDER BY start_date");
	}
	public static ArrayList<Reservation> reportReservation(String Id,String violation){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String queryString="UPDATE RESERVATION SET violation_type='"+violation+"' WHERE idreservation='"+Id+"'";		
		conn = SQLConnection.getDBConnection();
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();					 
		} catch (SQLException e) {}   
		return ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDRESERVATION = '"+Id+"' ORDER BY start_date");
	}
	
	public static void cancelReservation(Reservation reservation) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String cancelReservation = "DELETE FROM RESERVATION WHERE idreservation='"
										+ reservation.getIdreservation() + "'";
			stmt.executeUpdate(cancelReservation);	
			conn.commit(); 
		} catch (SQLException e) {}
	}


	public static void insertReservation(Reservation reservation) {  
		StoreListinDB(reservation,"INSERT INTO RESERVATION (iduser, idfacility, start_date, start_time) ");
	}
	
	public static ArrayList<Reservation>  listReservations() {  
			return ReturnMatchingReservationsList(" SELECT * from RESERVATION ORDER BY idreservation");
	}
	
	public static ArrayList<Reservation>  listSpecificReservations(String iduser) {  
		return ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDUSER = '"+ iduser +"' ORDER BY idreservation");
	}
	public static ArrayList<Reservation> listMyViolations(String iduser){
		return ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDUSER = '"+ iduser +"'and violation_type IS NOT NULL ORDER BY idreservation");
	}
	
	//determine if companyID is unique
	public static Boolean ReservationIDunique(String idresv)  {  
			return (ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDRESERVATION = '"+idresv+"' ORDER BY idfacility").isEmpty());
	}
	//search company with reservation ID
	public static ArrayList<Reservation>   searchReservation (String idresv)  {  
			return ReturnMatchingReservationsList(" SELECT * from RESERVATION WHERE IDRESERVATION = '"+idresv+"' ORDER BY idfacility");
	}
}