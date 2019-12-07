<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility Information</title>
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
    <form action="/company_management/FacilityController?action=modifyFacility&id=${FACILITIES.idfacility}" method="post">
    <table border="1" class="myTable"> 
    <tr>
    <td> Facility ID: </td>
    <td> <c:out value="${FACILITIES.idfacility}" /> </td>
    </tr>

    <tr>
    <td> Facility type: </td>
    <td> <c:out value="${FACILITIES.facility_type}" />  </td>
    </tr>
    
    <tr>
    <td> Facility Name: </td>
    <td> <c:out value="${FACILITIES.facility_name}" />  </td>
    </tr>
    
    <tr>
    <td> Duration: </td>
    <td> <c:out value="${FACILITIES.duration}" />  </td>
    </tr>
    
    <tr>
    <td> Venue: </td>
    <td> <c:out value="${FACILITIES.venue}" />  </td>
    </tr>
    
    <tr>
    <td> Interval: </td>
    <td> <c:out value="${FACILITIES.interval}" />  </td>
    </tr>
    
    <tr>
    <td> Deposit amount: </td>
    <td> <c:out value="${FACILITIES.deposit}" /> </td>
    </tr>
    <tr>
    
    <tr>
    <td> Availability: </td>
    <td><input type="radio" name="availability" value="Yes"> Yes<br><input type="radio" name="availability" value="No"> No<br></td>
    
    </tr>

    <tr>
    <td> Damage status: </td>
    <td><input type="radio" name="damage_status" value="Damaged"> Damaged<br><input type="radio" name="damage_status" value="Undamaged"> Undamaged<br></td>
    </tr>
    </table>
    <input type="submit" value="Confirm Modification" onclick="return confirm('Are you sure?')">
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