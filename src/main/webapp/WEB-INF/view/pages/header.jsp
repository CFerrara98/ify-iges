<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.css">
<link href="./resources/css/modern-business.css" rel="stylesheet">
<link href="./resources/css/style.css" rel="stylesheet">
<link rel="shortcut icon" href="/IFY/src/main/webapp/resources/images/favicon.ico" type="image/x-icon"/>
</head>
<body>

	<c:if test="${successoIscrizione != null}">
			<div class="alert alert-success alert-home alert-dismissible">
			  	<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong> ${successoIscrizione} </strong>
			</div>	
	</c:if>
	
	<c:if test="${successoConvenzionamento != null}">
		<div class="alert alert-success alert-home alert-dismissible">
			  	<button type="button" class="close" data-dismiss="alert">&times;</button>
			  	<strong> ${successoConvenzionamento} </strong>
			</div>	
	</c:if>
	
	
	

	
	<nav class="fixed-top navbar navbar-dark  bg-dark flex-md-nowrap p-0">
		<div>
			<a style="margin-left: 5px; text-decoration: none;" href="/">
				<img src="./resources/images/logos/logo pre login.png" style="width:50px;">
			</a>
			<a class="navbar-brand col-sm-3 col-md-2 mr-0 ify-title" href="./">Internship For You</a>
		</div>
		<div style="margin-right: 5px; display: flex; flex-direction: row; align-items: center;">
			
			<c:choose>
				<c:when test="${sessionScope.email!=null}">
					<div class="btn-group">
  						<div class="btn-group">
    						<button id="customDropdown" type="button" class="btn dropdown-toggle button-dash-login btn btn-block" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      							Ciao, ${utente.nome}
      						</button>
    					<div class="dropdown-menu custom-drop-menu" aria-labelledby="customDropdown">
      						<span class="dropdown-menu-arrow"></span>
      						<a class="dropdown-item custom-drop-menu-testo" href="./logout">Logout</a>
    					</div>
  						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${pageContext.request.servletPath == '/WEB-INF/view/pages/homepage.jsp'}">
						<a href="./loginPage" type="submit" class="login-btn btn btn-primary btn-block">Login</a>
					</c:when>
					<c:otherwise>
						<a href="./" type="submit" class="login-btn btn btn-primary btn-block">Homepage</a>
						
					</c:otherwise>
					</c:choose>					
				</c:otherwise>
			</c:choose>
			
			<figure class="figure">
				<img src="./resources/images/logos/logo dipartimento.png" height="50" width="50" class="figure-img img-fluid rounded logo" alt="Un'immagine generica segnaposto con angoli arrotondati in una figura.">
			</figure>
		</div>
	</nav>
	<!--  </div>-->
	
	
</body>
</html>