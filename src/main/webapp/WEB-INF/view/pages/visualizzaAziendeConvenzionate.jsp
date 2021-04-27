<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Aziende convenzionate</title>

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
				

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div class="container">

						<h4>
							<span class="my-4 header">Aziende Convenzionate</span>
						</h4>
						<input class="form-control" id="filter" type="text"
							placeholder="Filtra Aziende">
						<table class="table-sm" id="parentTable" data-toggle="table" data-sortable="true" data-detail-view="true" data-detail-view-icon="false" data-pagination="true" data-page-size="5">
							<thead>
								<tr>
									<th class= "d-none">Hidden nested details table</th>
									<th class= "titolo" data-sortable="true">Azienda</th>
									<th class= "titolo" data-sortable="true">Sede</th>
									<th class= "titolo" data-sortable="true">Settore</th>
									<th></th>
									<th></th>
								</tr>

							</thead>
							<tbody>

								<c:forEach items="${aziendeConvenzionate}" var="current"
									varStatus="loop">
									<tr>
										<td>
											<c:set var = "check" value = "${false}"/>
                        					<c:forEach items="${current.progettiFormativi}" var="progetto" varStatus="loop">
                          						<c:if test="${progetto.stato.equals('attivo')}"> 
                            						<c:set var = "check" value = "${true}"/>
                          						</c:if>
                        					</c:forEach>
                        	 				<c:if test="${check}">
                        	 				
											<table date-pagination="true" date-pagination-loop="false">
												<thead>
													<tr class="bg-dark" style="color: #fff;">
														<th data-sortable="true">Progetto</th>
														<th data-sortable="true">Attivazione</th>
														<th data-sortable="true">Ambito</th>
														<th data-sortable="true">Numero Tirocinanti</th>
														<th data-sortable="true"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${current.progettiFormativi}" var="progetto" varStatus="loop">
														<c:if test="${progetto.stato.equals('attivo')}">
															<tr>
																<td>${progetto.nome}</td>
																<td><fmt:parseDate  value="${progetto.data_compilazione}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" /><fmt:formatDate value="${parsedDate}" pattern = "dd-MM-yyyy"   type="date" var="stdDatum" /><c:out value="${stdDatum}"></c:out></td>
																<td>${progetto.ambito}</td>
																<td>${progetto.max_partecipanti}</td>
																<td>
																	<!--  
																	<input type="submit" class="btn btn-primary aziende-convenzionate-btn dettagli-btn" value="Dettagli">
																	-->
																	<form name="dettagliForm" method="POST" action="./visualizzaDettagliProgettoFormativoUtente">
																		<input type="hidden" name="idProgettoFormativo" value="${progetto.id}">
																			<button class="btn btn btn-primary aziende-convenzionate-btn dettagli-btn">
  																				Dettagli
																			</button>
																	</form>
																</td>
															</tr>
														</c:if>
													</c:forEach>
												</tbody>
											</table>
											
											</c:if>
											
										</td>
										<td class="testo-tabella">${current.ragioneSociale}</td>
										<td class="testo-tabella">${current.sede}</td>
										<td class="testo-tabella">${current.settore}</td>
										<td class="testo-tabella">
											<form method="post" action="./dettagliAzienda">
												<input type="hidden" name="pIva" value="${current.pIva}">
												<input type="submit" class="btn reg" value="Dettagli">
											</form>
										</td>
										<td class="testo-tabella"><a class="detail-icon btn reg" style="width: 160px;">Progetti Formativi</a></td>
											
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
	<!-- <script src="./resources/js/ajax.js"></script> -->
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