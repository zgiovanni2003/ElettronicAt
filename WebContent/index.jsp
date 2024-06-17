<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>E-Commerce Tec</title>

</head>
<body>
	<%@ include file="components/header.jsp" %>
	<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-body-tertiary">
	
    <div class="col-md-6 p-lg-5 mx-auto my-5">
    <img alt="" class="img-fluid" src="images/in.jpg">
      <h1 class="display-3 fw-bold">Progettato per creare</h1>
      <h3 class="fw-normal text-muted mb-3">Costruisci tutto ciò che vuoi</h3>
      <div class="d-flex gap-3 justify-content-center lead fw-normal">
      
        <a class="icon-link" href="prodotti.jsp">
          Vedi i nostri prodotti
          <svg class="bi"><use xlink:href="#chevron-right"/></svg>
        </a>
      </div>
    </div>
    <div class="product-device shadow-sm d-none d-md-block"></div>
    <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
  </div>
	<%@ include file="components/footer.jsp" %>
</body>
</html>