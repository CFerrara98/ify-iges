<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Progetti formativi attivi</title>
		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
		<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
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
							<ul class="list-unstyled components mb-5">
								<li><a href = "./">Dashboard</a></li>
								<li class="active"><a href="#homeSubmenuRichieste"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle active">Progetti formativi</a>
								<ul class="collapse list-unstyled" id="homeSubmenuRichieste">
									<li><a href="./nuovoProgettoFormativo">Nuovo progetto formativo</a></li>
									<li><a href="./progettiFormativiAttivi" class="active">Progetti formativi attivi</a></li>
									<li><a href="./progettiFormativiArchiviati">Progetti formativi archiviati</a></li>
								</ul>
								</li>
								<li><a href="#homeSubmenuDomande"
								data-toggle="collapse" aria-expanded="true"
								class="dropdown-toggle">Domande di tirocinio</a>
								<ul class="collapse list-unstyled" id="homeSubmenuDomande">
									<li><a href="./visualizzaDomandeTirocinioInAttesaAzienda">Domande in attesa</a></li>
									<li><a href="./visualizzaDomandeTirocinioInoltrateAzienda">Domande inoltrate</a></li>
								</ul></li>
								<li><a href="./visualizzaTirociniInCorsoAzienda">Tirocini in corso</a></li>
							</ul>
						</div>
					</nav>
					<!-- Page Content  -->
					<div id="content" class="p-4 p-md-5 pt-5">
						<div class="container">
							<h4>
								<span class="my-4 header">Progetti formativi attivi</span>
							</h4>
							<input class="form-control" id="filter" type="text"
							placeholder="Filtra Progetti">
							<table id="parentTable" data-toggle="table" data-sortable="true" data-pagination="true" data-page-size="5">
							<thead>
								<tr>
									<th class="detail" data-sortable="true">Nome progetto</th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${progettiFormativiAttivi}" var="current"
									varStatus="loop">
									<tr>
										<td class="testo-tabella">${current.nome}</td>
										<td class="testo-tabella">
												<form name="dettagliForm" method="POST"
												action="./visualizzaDettagliProgettoFormativoAttivo">
												<input type="hidden" name="idProgettoFormativo"
													value="${current.id}">
												<button class="btn reg">
														<i class="fa fa-info-circle"></i>
  														Dettagli
												</button>
											</form>
										</td>
											<td class="testo-tabella">
											<form name="modificaForm" method="POST"
												action="./visualizzaFormModificaProgettoFormativoAttivo">
												<input type="hidden" name="idProgettoFormativo"
													value="${current.id}">
												<button class="btn reg">
													<i class="fa fa-edit"></i>
													Modifica
												</button>
											</form>
											</td>
											<td class="testo-tabella">
											<form name="accettaForm" method="POST"
												action="./archiviaProgettoFormativo">
												<input type="hidden" name="idProgettoFormativo"
													value="${current.id}">
												<button class="btn reg" id="red">
													<i class="fa fa-folder"></i>
													Archivia
												</button>
											</form>
											</td>
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
	<c:if test="${message != null}">
		<%@ include file="modalNotifica.jsp"%>
	</c:if>
	<c:if test="${progettoPerDettagli!=null}">
		<%@ include file="modalDettagliProgetto.jsp"%>
	</c:if>
	<c:if test="${progettoPerModifica!=null}">
		<%@ include file="modalModificaProgettoAttivo.jsp"%>
	</c:if>
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