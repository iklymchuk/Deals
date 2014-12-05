<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Hello, ${user.username} </title>
</head>
<body>

<h2>Welcome, ${currentUser.username}! Your balance is: ${currentUser.balance}</h2>

<img src = "../home/iklymchuk/Desktop/IMG_20141201_101832.jpg"/>
<img src="<c:url value='/images/java_logo.png' />"/>

<img src = "../home/iklymchuk/Desktop/IMG_20141201_101832.jpg"/>

<img src="<%=request.getContextPath()%>/image/java_logo.png"/>

<img src='data:image/png;base64,<s:property value='encodedImage'/>' alt="java_logo.png" />

<img src="<html:rewrite page='<%=request.getContextPath()%>/image/java_logo.png'/>" width="10%" height="10%"/>


<img src="${currentUser.photo}" />
</body>
</html>