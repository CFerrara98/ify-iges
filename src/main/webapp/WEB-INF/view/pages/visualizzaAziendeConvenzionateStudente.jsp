<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Aziende convenzionate</title>


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
							<i class="fa fa-bars"></i> <span class="sr-only">Toggle
								Menu</span>
						</button>
					</div>
					<div class="p-4 pt-5">
						<!--  <h1><a href="index.html" class="logo">IFY</a></h1>-->


						<ul class="list-unstyled components mb-5">

							<li><a href="./">Dashboard</a></li>
							<li><a href="./visualizzaAziendeConvenzionateStudente"
								class="active">Aziende convenzionate</a></li>
							<li><a href="./visualizzaDomandeTirocinioInoltrateStudente">Domande
									di tirocinio</a></li>
							<li><a href="./visualizzaTirociniInCorsoStudente">Tirocini
									in corso</a></li>
						</ul>
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					
					<div class="container">

						<c:if test="${successoInserimentoDomanda != null}">
							<div
								class="alert alert-success alert-dashboard alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong> ${successoInserimentoDomanda} </strong>
							</div>
						</c:if>

						<h4>
							<span class="my-4 header">Aziende convenzionate</span>
						</h4>
						<input class="form-control" id="filter" type="text"
							placeholder="Filtra Aziende">
						<table class="table-sm" id="parentTable" data-toggle="table"
							data-sortable="true" data-detail-view="true"
							data-detail-view-icon="false" data-pagination="true"
							data-page-size="5">
							<thead>
								<tr>
									<th class="d-none">Hidden nested details table</th>
									<th class="titolo" data-sortable="true">Azienda</th>
									<th class="titolo" data-sortable="true">Sede</th>
									<th class="titolo" data-sortable="true">Settore</th>
									<th class="titolo"></th>
									<th class="titolo"></th>
								</tr>

							</thead>
							<tbody>

								<c:forEach items="${aziendeConvenzionate}" var="current"
									varStatus="loop">
									<tr>
										<td><c:set var="check" value="${false}" /> <c:forEach
												items="${current.progettiFormativi}" var="progetto"
												varStatus="loop">
												<c:if test="${progetto.stato.equals('attivo')}">
													<c:set var="check" value="${true}" />
												</c:if>
											</c:forEach> <c:if test="${check}">

												<table>
													<thead>
														<tr class="bg-dark" style="color: #fff;">
															<th data-sortable="true">Progetto</th>
															<th data-sortable="true">Attivazione</th>
															<th data-sortable="true">Ambito</th>
															<th data-sortable="true">Numero Tirocinanti</th>
															<th data-sortable="true"></th>
															<th data-sortable="true"></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${current.progettiFormativi}"
															var="progetto" varStatus="loop">
															<c:if test="${progetto.stato.equals('attivo')}">
																<tr>
																	<td>${progetto.nome}</td>
																	<td><fmt:parseDate
																			value="${progetto.data_compilazione}" type="date"
																			pattern="yyyy-MM-dd" var="parsedDate" />
																		<fmt:formatDate value="${parsedDate}"
																			pattern="dd-MM-yyyy" type="date" var="stdDatum" />
																		<c:out value="${stdDatum}"></c:out></td>
																	<td>${progetto.ambito}</td>
																	<td>${progetto.max_partecipanti}</td>
																	<td>
																		<!--  
																	<input type="submit" class="btn btn-primary aziende-convenzionate-btn dettagli-btn" value="Dettagli">
																	-->
																		<form name="dettagliForm" method="POST"
																			action="./visualizzaDettagliProgettoFormativoStudente">
																			<input type="hidden" name="idProgettoFormativo"
																				value="${progetto.id}">
																			<button
																				class="btn btn btn-primary aziende-convenzionate-btn dettagli-btn">
																				Dettagli</button>
																		</form>
																	</td>
																	<td>
																		
																		<form name="domandaTirocinioForm" method="POST"
																			action="./nuovaDomandaTirocinio">
																			<input type="hidden" name="idProgettoFormativo"
																				value="${progetto.id}">
																			<button
																				class="btn btn btn-primary aziende-convenzionate-btn invia-btn">
																				Invia domanda</button>
																		</form>
																	</td>
																</tr>
															</c:if>
														</c:forEach>
													</tbody>
												</table>

											</c:if></td>
										<td class="testo-tabella">${current.ragioneSociale}</td>
										<td class="testo-tabella">${current.sede}</td>
										<td class="testo-tabella">${current.settore}</td>
										<td class="testo-tabella">
											<form method="post" action="./dettagliAziendaStudente">
												<input type="hidden" name="pIva" value="${current.pIva}">
												<input type="submit" class="btn reg" value="Dettagli">
											</form>
										</td>
										<td class="testo-tabella"><a class="detail-icon btn reg"
											style="width: 160px;">Progetti Formativi</a></td>


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

	<c:if test="${AziendaPerDettagli != null}">
		<%@ include file="modalDettagliAzienda.jsp"%>
	</c:if>
	<c:if test="${progettoPerDettagli!=null}">
		<%@ include file="modalDettagliProgetto.jsp"%>
	</c:if>
	<c:if test="${message != null}">
		<%@ include file="modalNotifica.jsp"%>
	</c:if>
	<c:if test="${progettoFormativo!=null}">
		<%@ include file="modalInvioDomandeTirocinio.jsp"%>
	</c:if>

	<script>
		// Load detail view
		$('#parentTable').on('expand-row.bs.table',
				function(e, index, row, $detail) {

					// Get subtable from first cell
					var $rowDetails = $(row[0]);

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