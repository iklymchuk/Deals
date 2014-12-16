<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Welcome</title>
</head>
<body>

<h2>Welcome, ${currentUser.username}! Your balance is: ${currentUser.balance}</h2>


<form method="post" action="upload" >
<a href="${downloadPdf}">Download PDF</a>
<br/><br/>
</form>

<img class="imgs" border="0" src="<c:url value="/images/webapp/java_logo.png" />"\>
<img border="0" src="<c:url value="/home/iklymchuk/FL/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DealsServerLogging/images/testShowAvatar.jpeg" />"\>

<img src="<%=request.getRealPath("/")%>${currentUser.photo}" />

<h3>Your Users:</h3>

	<table class="data" border="1">
		<tr>
			<th><spring:message code="label.username" /></th>
			<th><spring:message code="label.role" /></th>
			<th><spring:message code="label.balance" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${assignUsers}" var="user">
			<tr>
				<td>${user.username}</td>
            	<td>${user.role}</td>
				<td>${user.balance}</td>
				<td>

				<a href="<%=request.getContextPath()%>/updateUser/${user.id}">Edit</a>
				   
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>