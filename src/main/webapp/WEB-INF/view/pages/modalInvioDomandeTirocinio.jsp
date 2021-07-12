<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/formStylePage.css" />"
	rel="stylesheet">

<div class="modal" id="modalFormDomanda" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-dark">
				<h5 class="modal-title" style="color: #fff;">Invio domanda di
					tirocinio del ${progettoFormativo.nome}</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form method="POST" action="./inserimentoDomandaTirocinio"
					modelAttribute="domandaTirocinioForm">
					<input type="hidden" name="idProgettoFormativo"
						value="${progettoFormativo.id}">
					<div class="form-group">
						<label for="formGroupExampleInput">Conoscenze tecniche: </label>
						<c:choose>
							<c:when test="${ConoscenzeError == null}">
								<input type="text" class="form-control"
									id="formGroupExampleInput" placeholder="Conoscenze tecniche"
									value="${domandaTirocinioForm.conoscenze}" name="conoscenze">
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control form-control is-invalid"
									id="formGroupExampleInput" placeholder="Conoscenze tecniche"
									name="conoscenze">
								<span class="myError">${ConoscenzeError}</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="docenteTutorId">Docente tutor: </label>
						<c:choose>
							<c:when test="${DocenteTutorError == null}">
								<select class="form-control" id="docenteTutorId" name="docenteTutorId">
									<c:forEach items="${docentiList}" var="docente" varStatus="loop">
										<option value = "${docente.id}">${docente.nome} ${docente.cognome}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<select class="form-control" id="docenteTutorId" name="docenteTutorId">
									<c:forEach items="${docentiList}" var="docente" varStatus="loop">
										<option value = "${docente.id}">${docente.nome} ${docente.cognome}</option>
									</c:forEach>
								</select>
								<span class="myError">${DocenteTutorError}</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput2">Motivazioni</label>
						<c:choose>
							<c:when test="${MotivazioniError == null}">
								<input type="text" class="form-control"
									id="formGroupExampleInput2" placeholder="Motivazioni"
									value="${domandaTirocinioForm.motivazioni}" name="motivazioni">
							</c:when>
							<c:otherwise>
								<input type="text" class="form-control form-control is-invalid"
									id="formGroupExampleInput2" placeholder="Motivazioni"
									name="motivazioni">
								<span class="myError">${MotivazioniError}</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput2">Numero CFU Tirocinio</label>
						<c:choose>
							<c:when test="${cfuError == null}">
								<input min="6" max="12" type="number" class="form-control"
									id="formGroupExampleInput2"
									value="${domandaTirocinioForm.numeroCFU}" name="numeroCFU">
							</c:when>
							<c:otherwise>
								<input min="6" max="12" type="number"
									class="form-control form-control is-invalid"
									id="formGroupExampleInput2" name="numeroCFU">
								<span class="myError">${cfuError}</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput2">Data inizio tirocinio</label>
						<c:choose>
							<c:when test="${dataInizioError == null}">
								<input type="date" class="form-control"
									id="formGroupExampleInput2"
									value="${domandaTirocinioForm.dataInizio}" name="dataInizio">
							</c:when>
							<c:otherwise>
								<input type="date" class="form-control form-control is-invalid"
									id="formGroupExampleInput2" name="dataInizio">
								<span class="myError">${dataInizioError}</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput2">Data fine tirocinio</label>
						<c:choose>
							<c:when test="${dataFineError == null}">
								<input type="date" class="form-control"
									id="formGroupExampleInput2"
									value="${domandaTirocinioForm.dataFine}" name="dataFine">
							</c:when>
							<c:otherwise>
								<input type="date" class="form-control form-control is-invalid"
									id="formGroupExampleInput2" name="dataFine">
								<span class="myError">${dataFineError}</span>
							</c:otherwise>
						</c:choose>
					</div>
  					<div class="form-check">
    					<input class="form-check-input" type="checkbox" id="accetto" name="condizioni">
							<label class="form-check-label" for="accetto"> Accetto</label>
							<a href="https://www.garanteprivacy.it/il-testo-del-regolamento" class="regolamento">Regolamento privacy</a>
					</div>
					<div class="form-group">
						<c:if test="${CondizioniError != null}">
							<span class="myError">${CondizioniError}</span>
						</c:if>
					</div>
					<input type="submit" value="Conferma" class="btn reg" />
				</form>
			</div>
		</div>
	</div>


</div>

<script>
	$(window).on('load', function() {
		$('#modalFormDomanda').modal('show');
	});
</script>