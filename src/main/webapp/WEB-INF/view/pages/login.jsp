<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Login</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.css">
<link href="<c:url value="/resources/css/formStylePage.css" />" rel="stylesheet">

<style>
footer {
	position: absolute!important;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>

<body background= "./resources/images/login.png">
<%@ include file="header.jsp" %>
	<div style="margin-top:100px; margin-bottom: 100px;" class="container">
	
		<form class="form-horizontal" role="form" name="loginForm"
			method="post" action="./login"
			modelAttribute="utenteForm">
			
			<h3>Login</h3>
			<div class="login-div row">
				<div class="col-6 login-inputs">
					<div class="form-group">
						<div class="col-sm-11 div-login">
							<c:choose>
								<c:when test="${EmailError == null}">
									<c:choose>
										<c:when test="${EmailPrecedente == null}">
											<input type="text" name="email" id="inputEmail" placeholder="Email" class="form-control">
										</c:when>
										<c:otherwise>
											<input type="text" name="email" id="inputEmail" value = "${EmailPrecedente}" class="form-control">
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<input type="text" name="email" id="inputEmail" placeholder="Email" class="form-control is-invalid">
									<span class = "myError">${EmailError}</span>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-11 div-login">
							<c:choose>
								<c:when test="${PasswordError == null}">
									<input type="password" name="password" id="inputPassword"
										placeholder="Password" class="form-control">
								</c:when>
								<c:otherwise>
									<input type="password" name="password" id="inputPassword"
										placeholder="Password" class="form-control is-invalid">
										<span class = "myError">${PasswordError}</span>	
								</c:otherwise>
							</c:choose>

						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-11">
							<c:if test="${CondizioniError != null}">
								<span class = "myError">${CondizioniError}</span>
							</c:if>
						</div>
						<button style="margin: 0 auto;" type="submit" id="reg" class="btn btn-primary btn-block">Login</button>
					</div>
				
				</div>
				
			</div>
		</form>
	</div>

	<%@ include file="footer.jsp" %>
	
	
	<c:if test="${message != null}">
		<%@ include file="modalNotifica.jsp"%>
	</c:if>
</body>
</html>