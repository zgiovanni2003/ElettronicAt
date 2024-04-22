<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<header>
	<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>">Home</a></li>
			<li><input type="search"></li>
			<%
			if(session.getAttribute("email")==null){
			%>
			<li><a href="<%=request.getContextPath()%>/login.jsp">Accedi</a></li>
			<%
			}else{
			%>
			<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
			<%
			}
			%>
			<li><a href="/">Carrello</a></li>
		</ul>
	</nav>
</header>
</body>
</html>