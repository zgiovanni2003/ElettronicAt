<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="scripts/login.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="components/header.jsp" %>
	<% if(session.getAttribute("email")!=null) response.sendRedirect(request.getContextPath()+"/index.jsp"); %>
		
	<div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Accedi</h3>
            <form action="<%=request.getContextPath()%>/login" method="post">
	
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="email" id="typeEmailX-2" class="form-control form-control-lg" name="email" required />
	              <label class="form-label" for="typeEmailX-2">Email</label>
	              <div id="email-error" class="form-text text-danger d-none"></div>
	            </div>
	
	            <div data-mdb-input-init class="form-outline mb-4">
	              <input type="password" id="typePasswordX-2" class="form-control form-control-lg" name="password" required />
	              <label class="form-label" for="typePasswordX-2">Password</label>
	              <div id="password-error" class="form-text text-danger d-none"></div>
	            </div>
	            
	            
	
	            <input class="btn btn-primary btn-lg btn-block" type="submit" value="Accedi">
            </form>
            <br>
            Se non sei registrato <a href="<%=request.getContextPath()%>/register.jsp">Registrati</a>
          </div>
        </div>
      </div>
    </div>
  </div>
	<%@ include file="components/footer.jsp" %>
</body>
</html>