<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company form</title>
</head>
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Company Management Application</a></h1></div>
      <div class="menu_nav">
      </div>
  </div>
<body>
<table>
  <tr>
   <td>
         <table border="1" class="myTable"> 
    <tr>
    <td> Company ID: </td>
    <td> <c:out value="${COMPANIES.idcompany}" /> </td>
    </tr>

    <tr>
    <td> Company Name: </td>
    <td> <c:out value="${COMPANIES.company_name}"/> </td>
    </tr>

    <tr>
    <td> Phone: </td>
    <td> <c:out value="${COMPANIES.phone}" /> </td>
    </tr>

    <tr>
    <td> Email: </td>
    <td> <c:out value="${COMPANIES.email}" /> </td>
    </tr>

    <tr>
    </tr>
    </table>
</td>
</tr>
</table>
</body>
</html>