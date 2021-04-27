<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/formStylePage.css" />" rel="stylesheet">


<div class="modal" id="modalModificaProgetto" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-dark">
				<h5 class="modal-title" style="color: #fff;">Modifica Progetto ${progettoPerModifica.nome}</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form method="POST" action="./modificaProgettoFormativoArchiviato" modelAttribute="modificaProgettoFormativoForm">
  					<input type="hidden" name="id" value="${progettoPerModifica.id}">
  					<div class="form-group">
    					<label for="formGroupExampleInput">Descrizione</label>
    					<c:choose>
								<c:when test="${DescrizioneError == null}">
									<input type="text" class="form-control" id="formGroupExampleInput" placeholder="Descrizione" value="${progettoPerModifica.descrizione}" name="descrizione">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control form-control is-invalid" id="formGroupExampleInput" placeholder="Descrizione" name="descrizione">
									<span class = "myError">${DescrizioneError}</span>
								</c:otherwise>
						</c:choose>
  					</div>
  					<div class="form-group">
    					<label for="formGroupExampleInput2">Conoscenze</label>
    					<c:choose>
								<c:when test="${ConoscenzeError == null}">
									<input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Conoscenze" value="${progettoPerModifica.conoscenze}" name="conoscenze">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control form-control is-invalid" id="formGroupExampleInput2" placeholder="Conoscenze" name="conoscenze">
									<span class = "myError">${ConoscenzeError}</span>
								</c:otherwise>
						</c:choose>
  					</div>
  					<div class="form-group">
    					<label for="formGroupExampleInput2">Max partecipanti</label>
    					<c:choose>
								<c:when test="${MaxPartecipantiError == null}">
									<input type="number" class="form-control" id="formGroupExampleInput2" value="${progettoPerModifica.max_partecipanti}" name="maxPartecipanti" min="1" max="999">
								</c:when>
								<c:otherwise>
									<input type="number" class="form-control form-control is-invalid" id="formGroupExampleInput2" name="maxPartecipanti" min="1" max="999">
									<span class = "myError">${MaxPartecipantiError}</span>
								</c:otherwise>
						</c:choose>
  					</div>
  					<input type="submit" value="Conferma" class="btn reg"/>
				</form>
			</div>
		</div>
	</div>


</div>

<script>
	$(window).on('load', function() {
		$('#modalModificaProgetto').modal('show');
	});
</script>