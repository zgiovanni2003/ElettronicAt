<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Registrati</h1>
	<form action="<%=request.getContextPath()%>/register" method="post">
		Nome: <input type="text" name="name" required> <br>
		Cognome: <input type="text" name="surname" required> <br>
		Data di nascita: <input type="date" name="born-date"> <br>
		Email: <input type="email" name="email" required> <br>
		Password: <input type="password" name="password" required> <br>
		<input type="submit" value="Registrati">
	</form>
	
</body>
</html>