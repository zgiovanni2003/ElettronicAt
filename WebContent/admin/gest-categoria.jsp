<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="it.zgiovanni2003.admin.CsrfTokenManager" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/addCategoria" method="post">
		<input type="hidden" name="csrfToken" value="<%= CsrfTokenManager.generateCsrfToken(request) %>">
		Nome categoria:<input type="text" name="nome_categoria">
		<input type="submit" value="Aggiungi">
	</form>
</body>
</html>