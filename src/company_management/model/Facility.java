package company_management.model;

import java.io.Serializable;
import company_management.data.FacilityDAO;

public class Facility implements Serializable{

	private static final long serialVersionUID = 3L;
	private String idfacility;
	private String facility_type;
	private String facility_name;
	private String interval;
	private String duration;
	private String venue;
	private String deposit;
	private String availability;
	private String damage_status;
	
	
	public void setFacility (String idfacility, String facility_type,String facility_name, String interval, String duration, String venue, String deposit, String availability, String damage_status) {
		setIdfacility(idfacility);
		setFacility_type(facility_type);
		setFacility_name(facility_name);
		setInterval(interval);
		setDuration(duration);
		setVenue(venue);
		setDeposit(deposit);
		setAvailability(availability);
		setDamage_status(damage_status);
	}
	
	public String getIdfacility() {
		return idfacility;
	}
	public void setIdfacility(String idfacility) {
		this.idfacility = idfacility;
	}
	
	public String getFacility_type() {
		return facility_type;
	}
	public void setFacility_type(String facility_type) {
		this.facility_type = facility_type;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
	}
	
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability){
		this.availability=availability;
	}
	
	public String getDamage_status() {
		return damage_status;
	}
	public void setDamage_status(String damage_status) {
		this.damage_status=damage_status;
	}

	private String validateIdfacility(String action, String idfacility) {
		String result="";
		if (!isTextAnInteger(idfacility))
			result="Your company ID must be a number";
		else
			if (action.equals("saveFacility")) {
				if (!stringSize(idfacility,3,16))
					result= "Your Company Id must between 3 and 16 digits";
				else
					if (!FacilityDAO.CompanyIDunique(idfacility))
						result="Company ID already in database";
			}
		return result;
	}

//	This section is for general purpose methods used internally in this class
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
}