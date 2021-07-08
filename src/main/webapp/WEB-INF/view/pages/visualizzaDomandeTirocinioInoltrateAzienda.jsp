<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  		<meta name="description" content="">
  		<meta name="author" content="">  
<title>Domande di tirocinio inoltrate</title>


<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />


<link rel="stylesheet" href="./resources/css/sidebar.css">
<link rel="stylesheet" href="./resources/css/style.css">
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
							<ul class="list-unstyled components mb-5">
								<li><a href = "./">Dashboard</a></li>
								<li><a href="#homeSubmenuRichieste"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle">Progetti formativi</a>
								<ul class="collapse list-unstyled" id="homeSubmenuRichieste">
									<li><a href="./nuovoProgettoFormativo">Nuovo progetto formativo</a></li>
									<li><a href="./progettiFormativiAttivi">Progetti formativi attivi</a></li>
									<li><a href="./progettiFormativiArchiviati">Progetti formativi archiviati</a></li>
								</ul>
								</li>
								<li><a href="#homeSubmenuDomande"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle active">Domande di tirocinio</a>
								<ul class="collapse list-unstyled" id="homeSubmenuDomande">
									<li><a href="./visualizzaDomandeTirocinioInAttesaAzienda">Domande in attesa</a></li>
									<li><a href="./visualizzaDomandeTirocinioInoltrateAzienda" class="active">Domande inoltrate</a></li>
								</ul></li>
								<li><a href="./visualizzaTirociniInCorsoAzienda">Tirocini in corso</a></li>
							</ul>
						</div>
					</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div class="container">

						<h4>
							<span class="my-4 header"> Domande di tirocinio inoltrate</span>
						</h4>
						<input class="form-control" id="filter" type="text"
							placeholder="Filtra Domande">
						<table id="parentTable" data-toggle="table" data-sortable="true" data-pagination="true" data-page-size="5">
							<thead>
								<tr>
									<th class="detail titolo" data-sortable="true">ID Domanda</th>
									<th data-sortable="true" class="titolo">Nome</th>
									<th data-sortable="true" class="titolo">Cognome</th>
									<th data-sortable="true" class="titolo">Matricola</th>
									<th data-sortable="true" class="titolo">Stato</th>
								</tr>

							</thead>
							<tbody>

								<c:forEach items="${domandeTirocinio}" var="current" varStatus="loop">
									<tr>
										<td class="testo-tabella">Domanda ${current.id}</td>
										<td class="testo-tabella">${current.studente.nome}</td>
										<td class="testo-tabella">${current.studente.cognome}</td>
										<td class="testo-tabella">${current.studente.matricola}</td>
										<c:choose>
											<c:when test="${current.stato == 'accettata_azienda'}">
												<td class="testo-tabella">In attesa del docente</td>
											</c:when>
											<c:otherwise>
												<td class="testo-tabella">${current.stato}</td>
											</c:otherwise>
										</c:choose>
									</tr>

								</c:forEach>

							</tbody>
						</table>					
					</div>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>

	<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="./resources/js/bootstrap-table.min.js"></script>
	<script src="./resources/js/sidebar.js"></script>
	
	
	<script>

		/*filtraggio campi*/
		$(document)
				.ready(
						function() {
							$("#filter")
									.on(
											"keyup",
											function() {
												var value = $(this).val()
														.toLowerCase();
												$("#parentTable tbody tr")
														.filter(
																function() {
																	$(this)
																			.toggle(
																					$(
																							this)
																							.text()
																							.toLowerCase()
																							.indexOf(
																									value) > -1)
																});
											});
						});

		//show modal
	</script>
</body>
</html>