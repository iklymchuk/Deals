<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User registration</title>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
<h3>User registration:</h3>

<form:form method="Post" action="registrationform.html"	commandName="user" enctype="multipart/form-data">

	<table>
	 	
        <tr>
            <td>First Name:<FONT color="red"><form:errors
                    path="fname" /></FONT></td>
        </tr>
        <tr>
            <td><form:input path="fname" /></td>
        </tr>

        <tr>
            <td>Last Name:<FONT color="red"><form:errors
                    path="lname" /></FONT></td>
        </tr>
        <tr>
            <td><form:input path="lname" /></td>
        </tr>

		<tr>
			<td>User Name:<FONT color="red"><form:errors
				path="username" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="username" /></td>
		</tr>
		
		<tr>
	 		<td>Upload photo if you want: </td>
	 	</tr>	
	 	<tr>
	 		<td><input type="file" name="image"></td>
        </tr>
        
		<tr>
			<td>Password:<FONT color="red"><form:errors
				path="password" /></FONT></td>
		</tr>
		<tr>
			<td><form:password path="password" /></td>
		</tr>

		<tr>
			<td>Confirm Password:<FONT color="red"><form:errors
				path="confirmPassword" /></FONT></td>
		</tr>
		<tr>
			<td><form:password path="confirmPassword" /></td>
		</tr>

		<tr>
			<td>Email:<FONT color="red"><form:errors path="email" /></FONT></td>
		</tr>
		<tr>
			<td><form:input path="email" /></td>
		</tr>

        <tr>
            <td>Shopname:<FONT color="red"><form:errors path="shopname" /></FONT></td>
        </tr>
        <tr>
            <td><form:input path="shopname" /></td>
        </tr>

        <tr>
            <td>Role:<FONT color="red"><form:errors path="role" /></FONT></td>
        </tr>
        
        <tr>
            <td>
                <select name="role" onchange="">
                    <option value="User">User</option>
                    <option value="Dealer">Dealer</option>
                    <option value="MasterDealer">MasterDealer</option>
                </select>
            </td>
        </tr>

	<tr>
		<td>
	            <%
	                ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Lf5R_8SAAAAAMF4zOwupGeh-iRf4frlWCxfQlLZ",
	                                    "6Lf5R_8SAAAAAB5pRnY0ScNWdKc8KhXxkQVSmAy-", false);
	            	out.print(c.createRecaptchaHtml(null, null));
	            %>   
		</td>
	</tr>
	
	<tr>
		<td>
		<c:if test="${errorMessage != null}">
		<font color="#ff0000">${errorMessage}</font>
		</c:if>
			
		</td>
	</tr>
	
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
	
</form:form>

<a href="login.html">Login</a>
</body>

</html>