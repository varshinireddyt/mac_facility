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
<title>Facility List</title>
</head>
<body>
<center><h2>Facility List</h2></center>
<br><br>

 <form action="/company_management/UserFacilityController?action=listSpecificFacility" method="post">           
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility ID</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Type</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Start Date</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Start Time</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Deposit</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Reserve</th>
			</tr>

 		<c:forEach items="${FACILITIES}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idfacility}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facility_type}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facility_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${DATE}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${TIME}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.deposit}" /></td>
			<td> <a href="/company_management/ReservationController?action=payReservation&iduser=${USER.userId}&idfacility=${item.idfacility}&start_date=${DATE}&start_time=${TIME}">Reserve</a></td>
			</tr>
		</c:forEach>
 </table>
 </form>

<script>
function myFunction() {
	var txt;
	  if (confirm("Reservation number : 1\n" +
			  "Facility Type : Multipurpose Rooms\n" +
			  "Facility Name : MR1\n" +
			  "Start date : October 15th, 2019 - Wednesday\n" +
			  "Start time : 1:00:00 PM\n" +
			  "End time : 2:00:00 PM\n" +  
			  "Deposit amount : $50" 
			  )) {
	    txt = "Reserved Successfully!";
	    window.alert("Reserved Successfully!");
	    location.replace("/company_management/ReservationController?action=saveReservation&id=${item.idfacility}")
	  } else {
	    txt = "Reservation Failed!";
	    window.alert("Reservation Failed!");
	  }
	  document.getElementById("demo").innerHTML = txt;
}
</script>

<div style="padding-right:10px; padding-left:10px">
<!-- <button style="float:right" type="button">Back</button> -->
<a href="<%=request.getContextPath()%>/searchFacility.jsp" style="float:left">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log Out</a>
</div><br>

</body>
</html>