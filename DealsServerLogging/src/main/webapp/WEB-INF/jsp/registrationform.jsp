<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User registration</title>
</head>
<body>
<h3>User registration:</h3>
Select photo to upload, if needed: <br />
<form method="post" action="upload" enctype="multipart/form-data">
    <input type="file" name="file" />
    <br/><br/>
    <input type="submit" value="Upload" />
</form>

<form:form method="Post" action="registrationform.html"
	commandName="user">
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
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>
<img src="/stickyImg"><br/>
<form method="post" action="captchaSubmit.jsp">
    Answer: <input name="answer" /><input type="submit" />
</form>

<br><br>
Already registered?
<br>
<a href="login.html">Login</a>
</body>

</html>