package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import company_management.util.SQLConnection;
import company_management.model.Facility;
public class FacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Facility> ReturnMatchingFacilitiesList (String queryString) {
		ArrayList<Facility> facilityListInDB = new ArrayList<Facility>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  

		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				Facility facility = new Facility(); 
				facility.setIdfacility(facilityList.getString("idfacility"));
				facility.setFacility_type(facilityList.getString("facility_type"));
				facility.setFacility_name(facilityList.getString("facility_name"));
				facility.setInterval(facilityList.getString("interval"));
				facility.setDuration(facilityList.getString("duration"));  
				facility.setVenue(facilityList.getString("venue"));  
				facility.setDeposit(facilityList.getString("deposit"));
				facility.setAvailability(facilityList.getString("availability"));
				facility.setDamage_status(facilityList.getString("damage_status"));
				facilityListInDB.add(facility);	
			}
		} catch (SQLException e) {}
		return facilityListInDB;
	}
	
	public static ArrayList<Facility>  listFacilities() {  
			return ReturnMatchingFacilitiesList("SELECT * from FACILITY ORDER BY cast(IDFACILITY as unsigned)");
	}
	
	//search companies
	public static ArrayList<Facility>  searchFacilities(String facilityname)  {  
			return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE facility_name LIKE '%"+facilityname+"%' ORDER BY idfacility");
	}
	public static ArrayList<Facility>  searchFacilitiestype(String facilityname)  {  
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE facility_type LIKE '%"+facilityname+"%' ORDER BY idfacility");
}
	
	//determine if companyID is unique
	public static Boolean CompanyIDunique(String idComp)  {  
			return (ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE IDFACILITY = '"+idComp+"' ORDER BY facility_name").isEmpty());
	}
	//search company with company ID
	public static ArrayList<Facility>   searchFacility (String idFacility)  {  
			return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE IDFACILITY = '"+idFacility+"' ORDER BY facility_name");
	}
	public static ArrayList<Facility> modifyFacility(String Id,String damage,String avai){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		String queryString="UPDATE FACILITY SET availability='"+avai+"',damage_status='"+damage+"'WHERE idfacility='"+Id+"'";		
		conn = SQLConnection.getDBConnection(); 
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);
			conn.commit();					 
		} catch (SQLException e) {}   
		return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE IDFACILITY = '"+Id+"' ORDER BY facility_name");
	}
}