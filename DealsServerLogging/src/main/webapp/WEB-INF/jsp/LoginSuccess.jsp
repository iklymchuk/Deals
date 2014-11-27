<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful Login</title>
</head>
<body>
<h1>Welcome, <core:out value="${user.username}"/></h1>

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
		<td>Your balance :</td>
		<td><core:out value="${user.balance}" /></td>
	</tr>
	<tr>
		<td>Your role :</td>
		<td><core:out value="${user.role}" /></td>
	</tr>
</table>

</body>
</html>
