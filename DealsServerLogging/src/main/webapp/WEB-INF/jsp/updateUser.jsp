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
<form:form method="POST" commandname="user" action="someUserPage">
	<table>
	
		<%-- Start Update isLock tr --%>
		<tr>
            <td>Lock user <FONT color="red"><form:errors path="islock" /></FONT></td>
            <td>
                <input type = "checkbox" name="lock">
            </td>
            
        </tr>
		<%-- Finish Update isLock tr --%>
		
		<%-- Start Update role tr --%>
		<tr>
            <td>Change role:<FONT color="red"><form:errors path="role" /></FONT></td>
            <td>
                <select name="role" onchange="">
                    <option value="User">User</option>
                    <option value="Dealer">Dealer</option>
                    <option value="MasterDealer">MasterDealer</option>
                </select>
            </td>
            
        </tr>
		<%-- Finish Update role tr --%>
     
		<tr>
			<td>Balance <FONT color="red"><form:errors path="balance" /></FONT></td>
			<td>
				<input type = "text" value="add balance">
			</td>
			
		</tr>
		
		<tr>
			<td>Assign <FONT color="red"><form:errors path="assign" /></FONT></td>
			<td>
				<input type = "text" value="assign">
			</td>
			
		</tr>

			<td>
            	<input value="Update" id = "update" type="submit">
            </td> 

	</table>
</form:form>
</body>

</html>
