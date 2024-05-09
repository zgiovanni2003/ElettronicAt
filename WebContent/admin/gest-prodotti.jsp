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
	<form action="<%=request.getContextPath()%>/addProdotto" method="post" enctype="multipart/form-data">
		<input type="hidden" name="csrfToken" value="<%= CsrfTokenManager.generateCsrfToken(request) %>">
		Nome <input type="text" name="nome_prodotto">
		Descrizione <textarea rows="3" cols="5" name="descrizione"></textarea>
		Prezzo <input type="text" name="prezzo">
		Categoria
		<select>
		
		</select>
		Carica Immagine<input type="file" name="file" accept=".jpg">
		<input type="submit" value="Aggiungi">
	</form>
</body>
</html>