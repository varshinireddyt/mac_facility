<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Reservation</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<table>
  <tr>
   <td>
   <form action="/company_management/ReservationController?action=reportReservation&id=${RESERVATION.idreservation}" method="post">
   <table border="1" class="myTable"> 
	<tr>
    <td> RESERVATION ID: </td>
    <td> <c:out value="${RESERVATION.idreservation}" /></td>
    </tr>
    <tr>
    <td> Facility type: </td>
    <td> <c:out value="${FACILITY.facility_type}" />  </td>
    </tr>
    
    <tr>
    <td> Facility Name: </td>
    <td> <c:out value="${FACILITY.facility_name}" />  </td>
    </tr>
    
    <tr>
    <td> Start Date: </td>
    <td> <c:out value="${RESERVATION.start_date}" />   </td>
    </tr>
    <tr>
    <td> Start Time: </td>
    <td> <c:out value="${RESERVATION.start_time}" /> </td>
    </tr>
    <tr>

    <tr>
    <td> Deposit amount: </td>
    <td> <c:out value="${FACILITY.deposit}" /> </td>
    </tr>
        
    <tr>
    <td> Violation Type: </td>
    <td> <input type="radio" name="violation_type" value="No-Show"> No-Show<br><input type="radio" name="violation_type" value="Violation"> Violation<br></td>
    </tr>
       

    </table>
    <input type="submit" value="Report" onclick="return confirm('Are you sure?')">
 </form>
<div style="width:700px; ">
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>
 
</td>
</tr>
</table>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>