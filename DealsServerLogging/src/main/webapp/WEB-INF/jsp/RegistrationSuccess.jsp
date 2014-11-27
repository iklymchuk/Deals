<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completed</title>
</head>
<body>
<h3>Welcome.</h3>
<table>
    <tr>
        <td>First Name :</td>
        <td><core:out value="${user.fname}" /></td>
    </tr>
    <tr>
        <td>Last Name :</td>
        <td><core:out value="${user.lname}" /></td>
    </tr>
	<tr>
		<td>User Name :</td>
		<td><core:out value="${user.username}" /></td>
	</tr>
	<tr>
		<td>Balance :</td>
		<td><core:out value="${user.balance}" /></td>
	</tr>
</table>

</body>
</html>