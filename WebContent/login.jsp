<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="components/header.jsp" %>
	<% if(session.getAttribute("email")!=null) response.sendRedirect(request.getContextPath()+"/index.jsp"); %>
	<h1>Login</h1>
	<form action="<%=request.getContextPath()%>/login" method="post">
		<input type="text" name="email">
		<input type="password" name="password">
		<input type="submit" value="Accedi">
	</form>
	Se non sei registrato <a href="<%=request.getContextPath()%>/register.jsp">Registrati</a>
	<%@ include file="components/footer.jsp" %>
</body>
</html>