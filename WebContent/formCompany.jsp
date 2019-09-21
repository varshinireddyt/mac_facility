<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company form</title>
</head>
<body>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="companyForm" action="/company_management/CompanyController?saveCompany" method="post">
    <table style="width: 1200px; ">
    <tr>
    <td> Company ID (*): </td>
    <td> <input name="idcompany" value="<c:out value='${company.idcompany}'/>" type="text" maxlength="16"> </td>
  	<td> <input name="companyIDerror"  value="<c:out value='${errorMsgs.companyIDerror}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Company Name (*): </td>
    <td> <input name="company_name" value="<c:out value='${company.company_name}'/>" type="text" maxlength="45">  </td>
 	<td> <input name="companyNameError"  value="<c:out value='${errorMsgs.companyNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Phone: </td>
    <td> <input name="phone" value="<c:out value='${company.phone}'/>" type="text" maxlength="16">  </td>
  	<td> <input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${company.email}'/>" type="text" maxlength="45">  </td>
  	<td> <input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="saveCompany" type="hidden">
    <input type="submit" value="Insert Company">
    </form>
</td>
</tr>
</table>
</body>
</html>