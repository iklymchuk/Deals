<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Hello MasterDealer</title>
</head>
<body>

<h2>Welcome MasterDealer</h2>

	<table class="data">
		
		<c:forEach items="${listUsers}" var="user">
			<tr>
				<td>${user.username}</td>
				<td>${user.role}</td>
				<td>${user.balance}</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>