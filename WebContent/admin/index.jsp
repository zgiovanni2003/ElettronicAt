<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<title>Admin Dashboard</title>
</head>
<body>
<%@ include file="components/header.jsp" %>
<%if(session.getAttribute("admin-email")==null) response.sendRedirect(request.getContextPath()+"/admin/login.jsp"); %>

	<div class="album py-4 ">
	    <div class="container">
		    <div id="card_container" class="row justify-content-center top-buffer g-4">
		        <div class="col">
		        	<div class="card" style="width: 18rem;">

					  <div class="card-body">
					    <h5 class="card-title">Prodotti</h5>
					    <p class="card-text">Gestisci i prodotti</p>
					    <a href="gest-prodotti.jsp" class="btn btn-primary">Accedi</a>
					  </div>
					</div>
		        </div>
		        <div class="col">
		        	<div class="card" style="width: 18rem;">

					  <div class="card-body">
					    <h5 class="card-title">Categorie</h5>
					    <p class="card-text">Gestisci le categorie</p>
					    <a href="gest-categoria.jsp" class="btn btn-primary">Accedi</a>
					  </div>
					</div>
		        </div>
		        <div class="col">
		        	<div class="card" style="width: 18rem;">

					  <div class="card-body">
					    <h5 class="card-title">Ordini</h5>
					    <p class="card-text">Gestisci gli ordini</p>
					    <a href="view-ordini.jsp" class="btn btn-primary">Accedi</a>
					  </div>
					</div>
		        </div>
		    </div>
		</div>
	</div>

	
<%@ include file="components/footer.jsp" %>
</body>
</html>
