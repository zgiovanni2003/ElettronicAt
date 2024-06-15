<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="scripts/carrello.js"></script>

<title>E-Commerce Tec</title>

</head>
<body>
	<%@ include file="components/header.jsp" %>
	<%
		if(session.getAttribute("email")!=null){
	%>
	<input type="button" value="Acquista" onclick="window.location.href = 'newOrdine';">
	<input type="button" value="Visualizza Ordini" onclick="window.location.href = 'view-ordini.jsp';">
	<%
		}
	%>
	<div class="table-responsive">
		<table id="prodotto_table" class="table table-striped">
	        <thead>
	            <tr>
	                <th>Nome Prodotto</th>
	                <th>Descrizione</th>
	                <th>Prezzo</th>
	                <th>Nome Categoria</th>
	                <th>Quantità</th>
	                <th>Elimina</th>
	            </tr>
	        </thead>
	        <tbody>
	            
	        </tbody>
	    </table>
	</div>
	
	<%@ include file="components/footer.jsp" %>
</body>
</html>