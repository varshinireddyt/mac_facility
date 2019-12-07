<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Payment</title>
</head>
<body>
<center><h2>Payment</h2></center>
<br><br>

<form action="/company_management/ReservationController?action=saveReservation" method="post">
	<input name="iduser" value="${USER.userId}" type="hidden">
	<input name="idfacility" value="${FACILITY.idfacility}" type="hidden">
	<input name="start_date" value="${DATE}" type="hidden">
	<input name="start_time" value="${TIME}" type="hidden">
	<div style="text-align:center">
		<table align="center" style="width:75%">
	  		<tr>
	    		<th>Credit Card Number</th>
	    		<td><input type="text" size="50" value="XXXX - XXXX - XXXX - XXXX"></input></td>
	  		</tr>
	  		<tr>
	    		<th>Expiration Date</th>
	    		<td><input type="text" size="50" value="XX / XX"></input></td>
	  		</tr>
	  		<tr>
	    		<th>PIN Number</th>
	    		<td><input type="text" size="50" value="XXX"></input></td>
	  		</tr>
		</table>
	</div><br>
	<input type="submit" value="Submit">
	</form>
<script>
function myFunction(p1, p2) {
	var txt;
	  if (confirm("Reservation number : 1\n" + p1 +
			  "Facility Type : Multipurpose Rooms\n" +
			  "Facility Name : MR1\n" +
			  "Start date : October 15th, 2019 - Wednesday\n" +
			  "Start time : 1:00:00 PM\n" +
			  "End time : 2:00:00 PM\n" +  
			  "Deposit amount : $50" 
			  )) {
	    txt = "Reserved Successfully!";
	    window.alert("Reserved Successfully!");
	    location.replace("/company_management/reservationList.jsp")
	  } else {
	    txt = "Reservation Failed!";
	    window.alert("Reservation Failed!");
	  }
	  document.getElementById("demo").innerHTML = txt;
}
</script>

<div style="padding-right:10px; padding-left:10px">
<!-- <button style="float:right" type="button">Back</button> -->
<a href="#" onclick="return goBack();" style="float:left">Back</a>
<a href="" style="float:right">Log Out</a>
</div><br>

</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>