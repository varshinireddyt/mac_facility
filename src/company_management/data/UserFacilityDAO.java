package company_management.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import company_management.util.SQLConnection;
import company_management.model.UserFacility;
public class UserFacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static ArrayList<UserFacility> ReturnMatchingFacilitiesList (String queryString) {
		ArrayList<UserFacility> facilityListInDB = new ArrayList<UserFacility>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  

		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				UserFacility facility = new UserFacility(); 
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
	
	public static ArrayList<UserFacility>  listFacilities() {  
			return ReturnMatchingFacilitiesList(" SELECT * from FACILITY ORDER BY facility_name");
	}
	
	//search companies
	public static ArrayList<UserFacility>  searchFacilities(String facilitytype)  {  
			return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE facility_type LIKE '%"+facilitytype+"%' ORDER BY idfacility");
	}
	
	//determine if companyID is unique
	public static Boolean CompanyIDunique(String idComp)  {  
			return (ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE IDFACILITY = '"+idComp+"' ORDER BY facility_name").isEmpty());
	}
	//search company with company ID
	public static ArrayList<UserFacility>   searchFacility (String idFacility)  {  
			return ReturnMatchingFacilitiesList(" SELECT * from FACILITY WHERE IDFACILITY = '"+idFacility+"' ORDER BY facility_name");
	}
}