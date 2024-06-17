<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="it.zgiovanni2003.model.CsrfTokenManager" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script src="scripts/categoria.js"></script>
</head>
<body>
<%@ include file="components/header.jsp" %>
<%if(session.getAttribute("admin-email")==null) response.sendRedirect(request.getContextPath()+"/admin/login.jsp"); %>
	<div class="">
	<br>  
	<p>
	  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseWidthExample" aria-expanded="false" aria-controls="collapseWidthExample">
	    Aggiungi Categoria
	  </button>
	</p>
	<div style="min-height: 20px;">
	  <div class="collapse collapse-horizontal" id="collapseWidthExample">
	    <div class="card card-body" style="width: 300px;">
	     	<form action="<%=request.getContextPath()%>/admin/categoria?action=add" method="post">
		        <input type="hidden" name="csrfToken" value="<%= CsrfTokenManager.generateCsrfToken(request) %>">
		        Nome categoria:<input type="text" name="nome_categoria">
		        <input type="submit" value="Aggiungi">
		    </form>
	    </div>
	  </div>
	</div>
	</div>
    
    
    <div class="table-responsive">
	    <table id="categoria_table" class="table table-striped">
	        <thead>
	            <tr>
	                <th>Nome Categoria</th>
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