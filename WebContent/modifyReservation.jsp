<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
  text-align: left;    
}
</style>
<title>Modify Reservation</title>
</head>
<body>
<center><h2>Reservation Detail</h2><center>
 <form name="modifyReservation" action="/company_management/ReservationController?modifyReservation" method="post">
 <input name="idreservation" value="<c:out value='${RESERVATION.idreservation}'/>" type="hidden"> 
 <input name="facility_type" value="<c:out value='${FACILITY.facility_type}'/>" type="hidden"> 
 <input name="facility_name" value="<c:out value='${FACILITY.facility_name}'/>" type="hidden">
 <input name="deposit" value="<c:out value='${FACILITY.deposit}'/>" type="hidden"> 
 <input name="violation_type" value="<c:out value='${RESERVATION.violation_type}'/>" type="hidden">  
<table align="center">
  <tr>
   <td>
   
    <table border="1" class="myTable">
	<tr>
    <td> RESERVATION ID: </td>
    <td> <input value="<c:out value='${RESERVATION.idreservation}'/>" maxlength="16" disabled="disabled"> </td>
    </tr>

    <tr>
    <td> Facility Type: </td>
    <td> <input value="<c:out value="${FACILITY.facility_type}"/>" maxlength="16" disabled="disabled"> </td>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td> <input value="<c:out value="${FACILITY.facility_name}"/>" maxlength="16" disabled="disabled"></td>
    </tr>
    
    <tr>
    <td> Start Date: </td>
    <td> <input name="start_date" value="<c:out value="${RESERVATION.start_date}"/>" maxlength="16" type="text"> </td>
    </tr>
    
    <tr>
    <td> Start Time: </td>
    <td> <input name="start_time" value="<c:out value="${RESERVATION.start_time}"/>" maxlength="16" type="text"> </td>
    </tr>

    <tr>
    <td> Deposit: </td>
    <td> <input value="<c:out value="${FACILITY.deposit}"/>" maxlength="16" disabled="disabled"> </td>
    </tr>
    
    <tr>
    <td> Violation Type: </td>
    <td> <input value="<c:out value="${RESERVATION.violation_type}"/>" maxlength="16" disabled="disabled"> </td>
    </tr>

    <tr>
    </tr>

    </table>
    </td>
    </tr>
    </table>
    <input name="action" value="modifyReservation" type="hidden">
    <input name="modifyReservationButton" type="submit" value="Modify">
    </form>
<div style="width:400px">
<form method="GET" action="<%=request.getContextPath()%>/modifyReservation.jsp">
</form>
<form action="<%=request.getContextPath()%>/LogoutController">
	<input type="button" onclick="goBack()" value="Back">
	<input style="margin-left: 200px" type="submit" value="Logout">
</form>
</div>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>