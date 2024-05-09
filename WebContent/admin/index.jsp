<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>
<%if(session.getAttribute("admin-email")==null) response.sendRedirect(request.getContextPath()+"/admin/login.jsp"); %>
Seleziona funzione:
	<button onclick="window.location.href = 'gest-prodotti.jsp';">Prodotti</button>
	<button onclick="window.location.href = 'gest-categoria.jsp';">Categoria</button>
	<button onclick="window.location.href = 'view-ordini.jsp';">Ordini</button>
</body>
</html>
