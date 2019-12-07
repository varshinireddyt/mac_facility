<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>User Information</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Facility Reservation Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<h2>User Information: </h2>
<table>
  <tr>
   <td>
   <form action="/company_management/UserAdminController?action=revokeUser&id=${USERS.userId}" method="post">
         <table border="1" class="myTable">
    <tr>
    <td> User ID: </td>
    <td> <c:out value="${USERS.userId}" /> </td>
    </tr>
     
    <tr>
    <td> User Name: </td>
    <td>  <c:out value="${USERS.userName}" /> </td>
    </tr>

    <tr>
    <td> First Name: </td>
    <td> <c:out value="${USERS.firstName}" /></td>
    </tr>

    <tr>
    <td> Last Name: </td>
    <td> <c:out value="${USERS.lastName}" /></td>
    </tr>
    
    <tr>
    <td> Email: </td>
    <td> <c:out value="${USERS.email}" /></td>
    </tr>
    
    <tr>
    <td> Role: </td>
    <td><c:out value="${USERS.role}" /></td>
    </tr>
     
    
    <tr><td><p>Revoke Status:</p></td>
    <td><input type="radio" name="Revoke" value="Yes"> Yes<br><input type="radio" name="Revoke" value="No"> No<br></td>
	
	</tr>

    <tr>
    <td> Number of no shows: </td>
    <td> 1 </td>
    </tr>

    <tr>
    <td> Number of violations: </td>
    <td> 0 </td>
    </tr>
    </table>
    <input type="submit" value="Confirm Status Change" onclick="return confirm('Are you sure?')">
 </form>
    </td>
    </tr>
</table>
<div style="width:400px">
<a href="<%=request.getContextPath()%>/adminViewSpecificUser.jsp" style="float:left">Back</a>
<a href="<%=request.getContextPath()%>/LogoutController" style="float:right">Log Out</a>
</div>
</body>
<script>
function goBack() {
  window.history.back();
}
</script>
</html>