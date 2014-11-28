<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User Profile</title>
</head>
<body>
<h3>Update User Profile:</h3>
<form:form method="POST" commandname="user" action="updateUser.html">
	<table>
        <tr>
        	</td> <input type = "text" value="add balance"></td>
        </tr>

        <tr>
            <td>Change role:<FONT color="red"><form:errors path="role" /></FONT></td>
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
            </td> <input type = "checkbox" value="lock"> Lock user? </td>
        </tr>
        
		<tr>
			<td><input value="Edit" type="submit"></td>
		</tr>
	</table>
</form:form>
</body>

</html>
