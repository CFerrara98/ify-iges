<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Nuovo progetto</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
<link href="<c:url value="/resources/css/formStylePage.css" />" rel="stylesheet">
<!-- Custom styles for this template -->

<link rel="stylesheet" href="./resources/css/sidebar.css">
<link href="./resources/css/dashboard.css" rel="stylesheet">

</head>

<body>
	<div class="container-fluid">
		
	<%@ include file="header.jsp"%>
		<div class="row">
			<div class="wrapper d-flex align-items-stretch">
				<nav id="sidebar">
					<div class="custom-menu">
						<button type="button" id="sidebarCollapse" class="btn btn-primary">
							<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
						</button>
					</div>
					<div class="p-4 pt-5">
						<!--  <h1><a href="index.html" class="logo">IFY</a></h1>-->
						
						
						<ul class="list-unstyled components mb-5">
							<li><a href="./">Dashboard</a></li>
							<li><a href="#homeSubmenuRichieste"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle active">Progetti formativi</a>
								<ul class="collapse list-unstyled" id="homeSubmenuRichieste">
									<li><a href="./nuovoProgettoFormativo" class="active">Nuovo progetto formativo</a></li>
									<li><a href="./progettiFormativiAttivi">Progetti formativi attivi</a></li>
									<li><a href="./progettiFormativiArchiviati">Progetti formativi archiviati</a></li>

								</ul>
							</li>
							<li><a href="#homeSubmenuDomande"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle">Domande di tirocinio</a>
								<ul class="collapse list-unstyled" id="homeSubmenuDomande">
									<li><a href="./visualizzaDomandeTirocinioInAttesaAzienda">Domande in attesa</a></li>
									<li><a href="./visualizzaDomandeTirocinioInoltrateAzienda">Domande inoltrate</a></li>

								</ul>
							</li>
							
							<li><a href="./visualizzaTirociniInCorsoAzienda">Tirocini in corso</a></li>
							

						</ul>
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div style="margin-top: 35px; class="container">
						<form class="form-horizontal" role="form" name="iscrizioneForm" method="post" action="./inserimentoProgettoFormativo" modelAttribute="InserimentoProgettoFormativoForm">
			            <div style="margin-top: 15px;">
						<h3>Nuovo progetto formativo</h3>
						</div>
						<div style="justify-content:center;"  class="row" >
							<div style="margin-top: 15px;" class="col-11">
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${NomeError == null}">
													<input type="text" name="nome" id="inputNome" placeholder="Nome" class="form-control" value="${nuovoProgettoForm.nome}">
												</c:when>
												<c:otherwise>
													<input type="text" name="nome" id="inputNome" placeholder="Nome" class="form-control is-invalid">
													<span class = "myError">${NomeError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									
									
									<div class="form-group">
										<div style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${DescrizioneError == null}">
													<textarea rows="3" id="descrizione" placeholder="Descrizione" name = "descrizione" class="form-control">${nuovoProgettoForm.descrizione}</textarea>
												</c:when>
												<c:otherwise>
													<textarea rows="3" id="descrizione" placeholder="Descrizione" name = "descrizione" class="form-control is-invalid"></textarea>
													<span class = "myError">${DescrizioneError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									
									
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${AmbitoError == null}">
													<input type="text" name="ambito" id="inputAmbito" placeholder="Ambito" class="form-control" value="${nuovoProgettoForm.ambito}">
												</c:when>
												<c:otherwise>
													<input type="text" name="ambito" id="inputAmbito" placeholder="Ambito" class="form-control is-invalid">
													<span class = "myError">${AmbitoError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${AttivitaError == null}">
													<textarea rows="2" id="inputAttivita" placeholder="Attivita di rilievo" name = "attivita" class="form-control">${nuovoProgettoForm.attivita}</textarea>
												</c:when>
												<c:otherwise>
													<textarea rows="2" id="inputAttivita" placeholder="Attivita di rilievo" name = "attivita" class="form-control is-invalid"></textarea>
													<span class = "myError">${AttivitaError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${ConoscenzeError == null}">
													<textarea rows="2" id="inputConoscenze" placeholder="Conoscenze necessarie" name = "conoscenze" class="form-control">${nuovoProgettoForm.conoscenze}</textarea>
												</c:when>
												<c:otherwise>
													<textarea rows="2" id="inputConoscenze" placeholder="Conoscenze necessarie" name = "conoscenze" class="form-control is-invalid"></textarea>
													<span class = "myError">${ConoscenzeError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<c:choose>
												<c:when test="${MaxPartecipantiError == null}">
													<input min="1" max="999" type="number" name="maxPartecipanti" id="inputPartecipanti" placeholder="Numero di partecipanti" class="form-control" value="${nuovoProgettoForm.maxPartecipanti}">
												</c:when>
												<c:otherwise>
													<input min="1" max="999" type="number" name="maxPartecipanti" id="inputPartecipanti" placeholder="Numero di partecipanti" class="form-control is-invalid">
													<span class = "myError">${MaxPartecipantiError}</span>
												</c:otherwise>
											</c:choose>
				
										</div>
									</div>
									<button type="submit" id="reg" class="btn btn-primary btn-block" style="margin: 25px 0 15px 45px">Inserisci</button>
								</div>
							</div>
						</form>
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