<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  		<meta name="description" content="">
  		<meta name="author" content="">
		<title>IFY - Internship For You</title>
		
    	<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.css">
  		<link href="./resources/css/modern-business.css" rel="stylesheet">
 		<link href="./resources/css/style.css" rel="stylesheet">
	</head>
	<body>
		<%@ include file="header.jsp" %>
  		<header>
			<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
		    	<ol class="carousel-indicators">
		        	<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		        	<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		        	<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		      	</ol>
		      	<div class="carousel-inner" role="listbox">
		        	<div class="carousel-item active" style="background-image: url('./resources/images/carosello/campus.jpg')">
		          		<div class="carousel-caption d-none d-md-block">
		            		<h3 class="testo">Dipartimento di Informatica dell'Università degli Studi di Salerno</h3>
		            		<p class="testo">L'università mette in contatto studenti e aziende</p>
		          		</div>
		        	</div>
		        	<div class="carousel-item" style="background-image: url('./resources/images/carosello/tirocini.jpg')">
		          		<div class="carousel-caption d-none d-md-block">
		            		<h3 class="testo">Tirocinio</h3>
		            		<p class="testo">IFY facilita agli studenti lo svolgimento di tirocini esterni</p>
		          		</div>
		        	</div>
		        	<div class="carousel-item" style="background-image: url('./resources/images/carosello/aziendacarousel.jpg')">
		          		<div class="carousel-caption d-none d-md-block">
		            		<h3 class="testo">Azienda</h3>
		            		<p class="testo">IFY agevola i progetti formativi presso le aziende</p>
		          		</div>
		        	</div>
		      	</div>
		      	<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		        	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		        	<span class="sr-only">Previous</span>
		      	</a>
		      	<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		        	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		        	<span class="sr-only">Next</span>
		      	</a>
		    </div>
		</header>
		<!-- Page Content -->
		<div class="container page-wrap">
			<h1 class="my-4 testosopracard">Benvenuto sulla piattaforma per la gestione dei tirocini</h1><br>
			<div class="row">
				<div class="col-lg-4 mb-4 card-hompage">
			      	<div class="card h-100">
			        	<h4 class="card-header">Aziende convenzionate</h4>
			          	<div class="card-body">
							<img src="./resources/images/card/handshake.png"  class="img-thumbnail">
							<p class="card-text">Visiona le aziende convenzionate con noi</p>
			          	</div>
			          	<div class="card-footer bottone-card-centro">
			            	<a href="./visualizzaAziendeConvenzionate" class="btn btn-primary bottonecard">Visualizza</a>
			          	</div>
			        </div>
			    </div>
				<div class="col-lg-4 mb-4 card-hompage"> <!-- aggiungi mx-auto per centrare-->
			    	<div class="card h-100">
			        	<h4 class="card-header">Sei uno studente?</h4>
			          		<div class="card-body">
								<img src="./resources/images/card/studente.png" class="img-thumbnail">
			            		<p class="card-text">Cosa aspetti a registrarti?</p>
			          		</div>
			          		<div class="card-footer bottone-card-centro">
			            		<a href="./iscrizioneStudente" class="btn btn-primary bottonecard">Iscriviti ora</a>
			          		</div>
			        </div>
			      </div>
			      <div class="col-lg-4 mb-4 card-hompage">
			      	<div class="card h-100">
			        	<h4 class="card-header">Sei un'azienda?</h4>
			          	<div class="card-body">
							<img src="./resources/images/card/azienda.png" class="img-thumbnail">
			            	<p class="card-text">Convenzionati in pochi passi</p>
			          	</div>
			          	<div class="card-footer bottone-card-centro">
			            	<a href="./iscrizioneAzienda" class="btn btn-primary bottonecard">Convenzionati ora</a>
			          	</div>
			        </div>
			      </div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
		<!-- Bootstrap core JavaScript -->
		<script src="./resources/vendor/jquery/jquery.min.js"></script>
		<script src="./resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		
	</body>
</html>