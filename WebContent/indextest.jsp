<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Company Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">

<!-- TO MAKE THE URL REFERENCES WORK YOU MUST HAVE SESSION ID DISABLED IN URL - SEE WEB.XML -->

      <div class="logo"><h1><a href="<c:url value='/' />">Company Management Application</a></h1></div>
  <div class="content">  

      <div class="menu_nav">
        <ul>
          <li><a href="/company_management/CompanyController?action=listCompany"  target="_top"><span>List Companies</span></a></li>
          <li><a href="formCompany.jsp"  target="_top"><span>Insert Company</span></a></li>  
          <li><a href="searchCompany.jsp"  target="_top"><span>Search Companies</span></a></li>  
          <li><a href="searchEmployee.jsp"  target="_top"><span>Search Employee</span></a></li>  
        </ul>
      </div>
    </div>
  </div>
  </div>
  </div>  
</body>
</html>