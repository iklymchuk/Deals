<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

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
		<td>
			<script type="text/javascript">var RecaptchaOptions = {theme : 'clean'};</script> 
			
			<c:if test="${invalidRecaptcha == true}">
                <span class="error_form_validation"><spring:message code="invalid.captcha" text="Invalid captcha please try again"/></span>
            </c:if>
			
			<%
			    ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LcW3OASAAAAAKEJTHMmp_bo5kny4lZXeDtgcMqC",
			    	"6LcW3OASAAAAAKVX2duVsSy2uMMHL105-jPDrHMD", false);
			    out.print(c.createRecaptchaHtml(null, null));
		    %>                
		</td>
	</tr>
	
	<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
	
</form:form>
<%-- 
<img src="/stickyImg"><br/>
<form method="post" action="captchaSubmit.jsp">
    Answer: <input name="answer" /><input type="submit" />
</form>

<br><br>
Already registered?
<br>
--%>

<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>

<a href="login.html">Login</a>
</body>

</html>