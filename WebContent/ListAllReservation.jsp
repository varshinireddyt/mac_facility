<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<title>Reservation List</title>
</head>
<body>
<center><h2>Reservation List</h2></center>

<span style="color:red"><%=(request.getAttribute("eMessage") == null) ? "" : request.getAttribute("eMessage")%></span>
         
       <table align="center" border="1" class="myTable"> 
			<tr class="myTableRow">
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Reservation ID</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Start Date</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Start Time</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">User Id</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Id</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">View</th> 
			</tr>

 		<c:forEach items="${RESERVATIONS}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idreservation}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.start_date}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.start_time}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.iduser}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idfacility}" /></td>
            <td> <a href="/company_management/ReservationController?action=managerViewSpecificReservation&idreservation=${item.idreservation}">View</a></td>
			</tr>
		</c:forEach>
 </table>


<div style="width:700px; ">
<a href="#" onclick="return goBack();">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log out</a>
</div>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>