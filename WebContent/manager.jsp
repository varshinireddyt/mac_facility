<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manager Page</title>
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Manager")== null) )
{
%>
<jsp:forward page="/login.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Manager's Home</h2></center>
 
Welcome <%=request.getAttribute("userName") %>
<ul>
<li><a href="managerSearchFacility.jsp"  target="_top"><span>View My Profile</span></a></li>  
<li><a href="managerSearchFacility.jsp"  target="_top"><span>Facility List</span></a></li> 
<li><a href="managerSearchFacility.jsp"  target="_top"><span>Search User</span></a></li> 
<li><a href="managerSearchFacility.jsp"  target="_top"><span>Search Facility</span></a></li>
<li><a href="managerSearchFacility.jsp"  target="_top"><span>View Reservation list</span></a></li> 

</ul>
 
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutController">Logout</a></div>
</body>
</html>