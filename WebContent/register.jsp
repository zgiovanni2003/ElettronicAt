<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<title>Insert title here</title>
</head>
<body>
	<%@ include file="components/header.jsp" %>
	<% if(session.getAttribute("email")!=null) response.sendRedirect(request.getContextPath()+"/index.jsp"); %>	

 <div class="container py-3 h-50">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Registrati</h3>
            <form action="<%=request.getContextPath()%>/register" method="post">
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="text" id="typeName" class="form-control form-control-lg" name="name" required />
	              <label class="form-label" for="typeName">Nome</label>
	            </div>
	            
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="text" id="typeSurname" class="form-control form-control-lg" name="surname" required />
	              <label class="form-label" for="typeSurname">Cognome</label>
	            </div>
	            
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="date" id="typeDate" class="form-control form-control-lg" name="born-date" required />
	              <label class="form-label" for="typeDate">Data di Nascita</label>
	            </div>
	
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="email" id="typeEmailX-2" class="form-control form-control-lg" name="email" required />
	              <label class="form-label" for="typeEmailX-2">Email</label>
	            </div>
	
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="password" id="typePasswordX-2" class="form-control form-control-lg" name="password" required />
	              <label class="form-label" for="typePasswordX-2">Password</label>
	            </div>
	            
	            
	
	            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Registrati">
            </form>
            <br>
            Se sei registrato <a href="<%=request.getContextPath()%>/login.jsp">Accedi</a>
          </div>
        </div>
      </div>
    </div>
  </div>
	
	
	
	<%@ include file="components/footer.jsp" %>
</body>
</html>