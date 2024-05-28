<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Navbar</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNavDropdown">
	      <ul class="navbar-nav me-auto">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/prodotti.jsp">Prodotti</a>
	        </li>
	      </ul>
	      <ul class="navbar-nav ms-auto">
	        <%
			if(session.getAttribute("email")==null){
			%>
	        <li class="nav-item">
	          <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Accedi</a>
	        </li>
	        <%
			}else{
			%>
	        <li class="nav-item">
	          <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
	        </li>
	        <%
			}
			%>
	        <li class="nav-item">
	          <a class="nav-link" href="<%=request.getContextPath()%>/carrello.jsp">Carrello</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>

</header>
</body>
</html>