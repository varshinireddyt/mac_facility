package company_management.model;

import java.io.Serializable;
import java.util.*;
import company_management.data.ReservationDAO;

public class Reservation implements Serializable{

	private static final long serialVersionUID = 3L;
	private String idreservation;
	private String iduser;
	private String idfacility;
	private String start_time;
	private String start_date;
	private String violation_type;
	
	
	public void setReservation (String idreservation, String iduser, String idfacility, String start_date, String start_time, String violation_type) {
		setIdreservation(idreservation);
		setIduser(iduser);
		setIdfacility(idfacility);
		setStart_date(start_date);
		setStart_time(start_time);
		setViolation_type(violation_type);
	}
	
	public String getIdreservation() {
		return idreservation;
	}
	public void setIdreservation(String idreservation) {
		this.idreservation = idreservation;
	}
	
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	
	public String getIdfacility() {
		return idfacility;
	}
	public void setIdfacility(String idfacility) {
		this.idfacility = idfacility;
	}
	
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
        this.start_time = start_time;
	}
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
        this.start_date = start_date;
	}
	
	public String getViolation_type() {
		return violation_type;
	}
	public void setViolation_type(String violation_type) {
		this.violation_type = violation_type;
	}

	private String validateIdreservation(String action, String idreservation) {
		String result="";
		if (!isTextAnInteger(idreservation))
			result="Your company ID must be a number";
		else
			if (action.equals("saveReservation")) {
				if (!stringSize(idreservation,3,16))
					result= "Your Company Id must between 3 and 16 digits";
				else
					if (!ReservationDAO.ReservationIDunique(idreservation))
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