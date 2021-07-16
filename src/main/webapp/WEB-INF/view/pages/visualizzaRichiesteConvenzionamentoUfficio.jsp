<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Gestione richieste di convenzionamento in attesa</title>
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
								<i class="fa fa-bars"></i> <span class="sr-only">Toggle
									Menu</span>
							</button>
						</div>
						<div class="p-4 pt-5">	
							<ul class="list-unstyled components mb-5">	
								<li><a href="./">Dashboard</a></li>
								<li><a href="#homeSubmenuRichieste" data-toggle="collapse" aria-expanded="true" class="dropdown-toggle active">Richieste</a>
									<ul class="collapse list-unstyled" id="homeSubmenuRichieste">
										<li><a href="./visualizzaRichiesteIscrizione">Richieste di iscrizione</a></li>
										<li><a href="./visualizzaRichiesteConvenzionamento" class="active">Richieste di convenzionamento</a></li>	
									</ul></li>
								<li><a href="#homeSubmenuDomande"
									data-toggle="collapse" aria-expanded="true"
									class="dropdown-toggle">Domande di tirocinio</a>
									<ul class="collapse list-unstyled" id="homeSubmenuDomande">
										<li><a href="./visualizzaDomandeTirocinioInAttesaUfficio">Domande in attesa</a></li>
										<li><a href="./visualizzaDomandeTirocinioValutateUfficio">Domande valutate</a></li>	
									</ul></li>	
								<li><a href="./visualizzaTirociniInCorsoUfficio">Tirocini in corso</a></li>	
							</ul>
						</div>
					</nav>
	
					<!-- Page Content  -->
					<div id="content" class="p-4 p-md-5 pt-5">
						<div class="container">	
							<h4>
								<span class="my-4 header">Richieste di convenzionamento in attesa</span>
							</h4>
							<input class="form-control" id="filter" type="text"
								placeholder="Filtra Richieste">
							<table id="parentTable" data-toggle="table" data-sortable="true"
								data-detail-view="true" data-pagination="true" data-page-size="5">
								<thead>
									<tr>
										<th class="d-none">Hidden nested details table</th>
										<th class="detail"></th>
										<th class="detail"></th>
										<th class="detail titolo" data-sortable="true">ID Richiesta</th>
										<th data-sortable="true" class="titolo">Azienda</th>	
									</tr>	
								</thead>
								<tbody>	
									<c:forEach items="${richiesteConvenzionamento}" var="current" varStatus="loop">
										<tr>
											<td>
												<dl>
													<dt>Ragione sociale:</dt>
													<dd>${current.azienda.ragioneSociale}</dd>
													<br>
													
													<dt>Email:</dt>
													<dd>${current.delegatoAziendale.email}</dd>
													<br>
	
													<dt>Sede:</dt>
													<dd>${current.azienda.sede}</dd>
													<br>
													
													<dt>Indirizzo:</dt>
													<dd>${current.delegatoAziendale.indirizzo}</dd>
													<br>
													
													<dt>Settore:</dt>
													<dd>${current.azienda.settore}</dd>
													<br>
	
													<dt>Descrizione:</dt>
													<dd>${current.azienda.descrizione}</dd>
													<br>
													<dt>Partita IVA:</dt>
													<dd>${current.azienda.partitaIva}</dd>
	
												</dl>
											</td>
											<td class="valuta testo-tabella">
												<form name="accettaForm" method="POST"
													action="./accettaRichiestaConvenzionamento">
													<input type="hidden" name="idRichiesta"
														value="${current.id}">
													<button class="btn btn-success" data-toggle="tooltip" title="Accetta richiesta">
														<i class="fa fa-user-check"></i>
													</button>
												</form>
											<td class="valuta testo-tabella">
												<form name="submitForm" method="POST"
													action="./rifiutaRichiestaConvenzionamento">
													<input type="hidden" name="idRichiesta"
														value="${current.id}">
													<button class="btn btn-danger" data-toggle="tooltip" title="Rifiuta richiesta">
														<i class="fa fa-user-times"></i>
													</button>
												</form>
											</td>
											<td class="testo-tabella">Richiesta ${current.id}</td>
											<td class="testo-tabella">${current.azienda.ragioneSociale}</td>	
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

	<script>
		// Load detail view
		$('#parentTable').on('expand-row.bs.table',
				function(e, index, row, $detail) {

					// Get subtable from first cell
					var $rowDetails = $(row[0]);

					// Give new id to avoid conflict with first cell    
					var id = $rowDetails.attr("id");
					$rowDetails.attr("id", id + "-Show");

					// Write rowDetail to detail
					$detail.html($rowDetails);

					return;

				})

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