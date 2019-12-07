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
   <form action="/company_management/UserAdminController?action=modifyUser&id=${USERS.userId}" method="post">
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
    <td><input type="radio" name="Role" value="Admin"> Admin<br><input type="radio" name="Role" value="Manager"> Facility Manager<br><input type="radio" name="Role" value="User"> User<br></td>

    </tr>
        
    </table>
    <input type="submit" value="Confirm Modification" onclick="return confirm('Are you sure?')">
 </form>
    </td>
    </tr>
</table>
<div style="width:400px">
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