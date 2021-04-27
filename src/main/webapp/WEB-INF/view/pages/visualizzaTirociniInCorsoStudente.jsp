<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  		<meta name="description" content="">
  		<meta name="author" content="">  
<title> Tirocinio in corso</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
<!-- Custom styles for this template -->

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
						<ul class="list-unstyled components mb-5">						
							<li><a href="./">Dashboard</a></li>
							<li><a href="./visualizzaAziendeConvenzionateStudente">Aziende convenzionate</a></li>
							<li><a href="./visualizzaDomandeTirocinioInoltrateStudente">Domande di tirocinio</a></li>
							<li><a href="./visualizzaTirociniInCorsoStudente" class="active">Tirocini in corso</a></li>
						</ul>
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div class="container">

						<h4>
							<span class="my-4 header"> Tirocini in corso </span>
						</h4>
						<input class="form-control" id="filter" type="text"
							placeholder="Filtra Tirocini">
						<table id="parentTable" data-toggle="table" data-sortable="true"
							data-detail-view="true" data-pagination="true" data-page-size="5">
							<thead>
								<tr>
									<th class="d-none">Hidden nested details table</th>
									<th data-sortable="true" class="titolo">Azienda</th>
									<th data-sortable="true" class="titolo">Progetto</th>
									<th data-sortable="true" class="titolo">Data Inizio</th>
								</tr>

							</thead>
							<tbody>

								<c:forEach items="${tirociniInCorso}" var="current"
									varStatus="loop">
									<tr>
										<td>
											<dl>
												
												<dt>Sede azienda:</dt>
												<dd>${current.azienda.sede}</dd>
												<br>
												
												<dt>Settore azienda:</dt>
												<dd>${current.azienda.settore}</dd>
												<br>
												
												<dt>Attivita del tirocinio:</dt>
												<dd>${current.progettoFormativo.attivita}</dd>
												<br>
												
												<dt>Conoscenze Personali:</dt>
												<dd>${current.conoscenze}</dd>
												<br>

												<dt>Motivazioni Personali:</dt>
												<dd>${current.motivazioni}</dd>
												<br>
												
												<dt>Data fine:</dt>
												<dd><fmt:parseDate  value="${current.dataFine}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" /><fmt:formatDate value="${parsedDate}" pattern = "dd-MM-yyyy"   type="date" var="stdDatum" /><c:out value="${stdDatum}"></c:out></dd>
												<br>
												
												<dt>Numero cfu:</dt>
												<dd>${current.cfu}</dd>
												<br>
												
												
												

											</dl>
										
										</td>
											
										<td>${current.azienda.ragioneSociale}</td>
										<td>${current.progettoFormativo.nome}</td>
										<td><fmt:parseDate  value="${current.dataInizio}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" /><fmt:formatDate value="${parsedDate}" pattern = "dd-MM-yyyy"   type="date" var="stdDatum" /><c:out value="${stdDatum}"></c:out></td>
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
		<%@ include file="modalDettagliAzienda.jsp" %>
	</c:if>
	<c:if test="${progettoPerDettagli!=null}">
		<%@ include file="modalDettagliProgetto.jsp"%>
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







</body>
</html>