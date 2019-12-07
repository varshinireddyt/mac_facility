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
<title>Reservation Detail</title>
</head>
<body>
<center><h2>Reservation Detail</h2></center><br><br>


<input name="iduser" value="<c:out value='${RESERVATION.iduser}'/>" type="hidden"> 
<table align="center" style="width:75%">
  <tr>
   <td>
         <table border="1" class="myTable"> 
    <tr>
    <td> RESERVATION ID: </td>
    <td> <c:out value="${RESERVATION.idreservation}" /> </td>
    </tr>

    <tr>
    <td> Facility Type: </td>
    <td> <c:out value="${FACILITY.facility_type}"/> </td>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td> <c:out value="${FACILITY.facility_name}" /> </td>
    </tr>
    
    <tr>
    <td> Start Date: </td>
    <td> <c:out value="${RESERVATION.start_date}" /> </td>
    </tr>
    
    <tr>
    <td> Start Time: </td>
    <td> <c:out value="${RESERVATION.start_time}" /> </td>
    </tr>

    <tr>
    <td> Deposit: </td>
    <td> <c:out value="${FACILITY.deposit}" /> </td>
    </tr>
    
    <tr>
    <td> Violation Type: </td>
    <td> <c:out value="${RESERVATION.violation_type}" /> </td>
    </tr>

    <tr>
    </tr>
    </table>
</td>
</tr>
</table>



<div align="center">
<form action="<%=request.getContextPath()%>/LogoutController">
	<input type="button" onclick="goBack()" value="Back">
	<input style="margin-left: 200px" type="submit" value="Logout">
</form>
</div>
<br>

</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>