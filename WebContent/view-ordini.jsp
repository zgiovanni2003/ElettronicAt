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
<script src="scripts/ordini.js"></script>
<title>E-Commerce Tec</title>

</head>
<body>
<%if(session.getAttribute("email")==null) response.sendRedirect(request.getContextPath()+"/login.jsp"); %>
	<%@ include file="components/header.jsp" %>
	
	<div class="table-responsive">
		<table id="prodotto_table" class="table table-striped">
	        <thead>
	            <tr>
	            	<th>Ordine n.</th>
	            	<th>Data acquisto</th>
	                <th>Nome Prodotto</th>
	                <th>Prezzo</th>
	                <th>Quantità</th>
	            </tr>
	        </thead>
	        <tbody>
	            
	        </tbody>
	    </table>
	</div>
	
	<%@ include file="components/footer.jsp" %>
</body>
</html>