<%--
Created by IntelliJ IDEA.
User: Eugene
Date: 22.11.2014
Time: 16:21
To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Login Page</title></head>
<body>
<center><h3>Login Page</h3> <br/> <form:form commandName="user" action="login.html" method="POST" name="user">
Username:<form:input path="username"/> <font color="red"><form:errors path="username"/></font><br/>
Password:<form:password path="password"/> <font color="red"><form:errors path="password"/></font><br>
<input type="submit" value="Login"/>
</form:form></center>
</body>
</html>
