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
<h3>Update ${user.username} Profile. Current balance is ${user.balance}.</h3>

<h3>Balance your manager is ${assignUser.balance}.</h3>

<form:form method="POST" commandName="user" action="${contextPath}/updateUser/${user.id}"> 

	<table>

		<%-- Start Update role tr --%>
		<tr>
            <td>Role:<FONT color="red"><form:errors path="role" /></FONT></td>
            <td>
                <select name="role" onchange="">
                    <option value="User">User</option>
                    <option value="Dealer">Dealer</option>
                    <option value="MasterDealer">MasterDealer</option>
                </select>
            </td>
            
        </tr>

		<tr>
            <td>Lock:<FONT color="red"><form:errors path="islock" /></FONT></td>
            <td>
                <select name="islock" onchange="">
                    <option value="no">no</option>
                    <option value="yes">yes</option>
                </select>
            </td>
            
        </tr>

      	<tr>
       		<td><form:label path="balance">Balance</form:label></td>
       		<td><form:input path="balance" /></td> 
    	</tr>
      
        <tr>
       		<td><form:label path="assign">Assign</form:label></td>
       		<td><form:input path="assign" /></td> 
    	</tr>
   
		<tr>
			<td>
            	<input value="Update" id = "update" type="submit">
            </td> 
        </tr>

	</table>
</form:form>
</body>

</html>
