<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../components/header.jsp" %>
	<% if(session.getAttribute("admin-email")!=null) response.sendRedirect(request.getContextPath()+"/admin/index.jsp"); %>
	<h1>Login</h1>
	<form action="<%=request.getContextPath()%>/adminLogin" method="post">
		<input type="text" name="email">
		<input type="password" name="password">
		<input type="submit" value="Accedi">
	</form>
	<%@ include file="../components/footer.jsp" %>
</body>
</html>