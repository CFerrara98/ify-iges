<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Dashboard</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link rel="stylesheet" href="./resources/css/sidebar.css">
<link href="./resources/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="wrapper d-flex align-items-stretch">
				<nav id="sidebar">
					<div class="custom-menu">
						<button type="button" id="sidebarCollapse" class="btn btn-primary">
							<i class="fa fa-bars"></i> <span class="sr-only">Toggle
								Menu</span>
						</button>
					</div>
					<div class="p-4 pt-5">
						<!--  <h1><a href="index.html" class="logo">IFY</a></h1>-->


						<ul class="list-unstyled components mb-5">

							<li><a href="./" class="active">Dashboard</a></li>
							<li><a href="#homeSubmenuRichieste" data-toggle="collapse"
								aria-expanded="true" class="dropdown-toggle">Progetti
									formativi</a>
								<ul class="collapse list-unstyled" id="homeSubmenuRichieste">
									<li><a href="./nuovoProgettoFormativo">Nuovo progetto
											formativo</a></li>
									<li><a href="./progettiFormativiAttivi">Progetti
											formativi attivi</a></li>
									<li><a href=".progettiFormativiArchiviati">Progetti
											formativi archiviati</a></li>

								</ul></li>
							<li><a href="#homeSubmenuDomande" data-toggle="collapse"
								aria-expanded="true" class="dropdown-toggle">Domande di
									tirocinio</a>
								<ul class="collapse list-unstyled" id="homeSubmenuDomande">
									<li><a href="./visualizzaDomandeTirocinioInAttesaAzienda">Domande
											in attesa</a></li>
									<li><a href="./visualizzaDomandeTirocinioInoltrateAzienda">Domande
											inoltrate</a></li>

								</ul></li>

							<li><a href="./visualizzaTirociniInCorsoAzienda">Tirocini
									in corso</a></li>
						</ul>
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">

					<div class="container">

						<c:if test="${successoInserimento != null}">
							<div
								class="alert alert-success alert-dashboard alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong> ${successoInserimento} </strong>
							</div>
						</c:if>

						<h4 class="h4-spazio">
							<span class="my-4 header">Benvenuto ${utente.nome} nella
								tua area personale</span>
						</h4>
						<div class="card-deck">
							<div class="card border-dark bordo-carta dimensione-carta">
								<img src="./resources/images/dashDelegato/ProgettiFormativi.jpg"
									class="card-img-top .my_img img-responsive" alt="..."
									height="232" width="100">
								<div class="card-body">
									<h5 class="card-title">Progetti Formativi</h5>
									<p class="card-text">In questa sezione puoi vedere i
										progetti formativi</p>
									<a type="button"
										class="btn btn-primary login-btn btn-dimensione bottonecard"
										href="./progettiFormativiAttivi">Progetti Formativi</a>
								</div>
							</div>
							<div class="card border-dark bordo-carta dimensione-carta">
								<img src="./resources/images/dashDelegato/DomandeTirocinio.jpg"
									class="card-img-top .my_img img-responsive" alt="..."
									height="232" width="100">
								<div class="card-body">
									<h5 class="card-title">Domande di tirocinio</h5>
									<p class="card-text">In questa sezione puoi vedere le tue
										domande di tirocinio</p>
									<a type="button"
										class="btn btn-primary login-btn btn-dimensione bottonecard"
										href="./visualizzaDomandeTirocinioInAttesaAzienda">Domande
										Tirocinio</a>
								</div>
							</div>
							<div class="card border-dark bordo-carta dimensione-carta">
								<img src="./resources/images/dashDelegato/TirociniInCorso.jpg"
									class="card-img-top .my_img img-responsive" alt="..."
									height="232" width="100">
								<div class="card-body">
									<h5 class="card-title">Tirocini in corso</h5>
									<p class="card-text">In questa sezione puoi vedere i
										tirocini in corso</p>
									<a type="button"
										class="btn btn-primary login-btn btn-dimensione bottonecard"
										href="./visualizzaTirociniInCorsoAzienda">Tirocini in
										corso</a>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="./resources/js/sidebar.js"></script>
</html>