<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Richiesta convenzionamento</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.css">
<link href="<c:url value="/resources/css/formStylePage.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>

<body background= "./resources/images/convenzione.png">
<%@ include file="header.jsp" %>
	<div style="margin-top: 50px; margin-bottom: 30px;" class="container">
            <form class="form-horizontal" role="form" name="convenzioneForm"
				method="post" action="./richiestaConvenzionamento"
				modelAttribute="convenzioneForm">
				
                <h3>Richiesta convenzionamento</h3>
                <div class = "row">
					<div class = "col-6">
					<h5>Dati delegato</h5>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${NomeError == null}">
										<input type="text" name="nome" id="inputNome" placeholder="Nome" 
											class="form-control" value="${convenzioneForm.nome}">
									</c:when>
									<c:otherwise>
										<input type="text" name="nome" id="inputNome" placeholder="Nome" class="form-control is-invalid">
										<span class="myError">${NomeError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${CognomeError == null}">
										<input type="text" name="cognome" id="inputCognome" placeholder="Cognome" 
											class="form-control" value="${convenzioneForm.cognome}">
									</c:when>
									<c:otherwise>
										<input type="text" name="cognome" id="inputCognome" placeholder="Cognome" class="form-control is-invalid">
										<span class="myError">${CognomeError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3">Sesso</label>
								<c:choose>
									<c:when test="${SessoError == null} && convenzioneForm.sesso == 'F'}">
										<label class="sesso radio-inline col-sm-2"> <input type="radio" name="sesso" id="maschio" value="M"> M </label>
										<label class="sesso radio-inline col-sm-2"> <input type="radio" name="sesso" id="femmina" value="F" checked> F </label>
									</c:when>
									<c:otherwise>
										<label class="sesso radio-inline col-sm-2"> <input type="radio" name="sesso" id="maschio" value="M" checked> M </label>
										<label class="sesso radio-inline col-sm-2"> <input type="radio" name="sesso" id="femmina" value="F"> F </label>
										<c:if test="${SessoError != null}">
											<span class="myError">${SessoError}</span>
										</c:if>
									</c:otherwise>
								</c:choose>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${RuoloError == null}">
										<input type="text" name="ruolo" id="inputRuolo" placeholder="Ruolo" 
											class="form-control" value="${convenzioneForm.ruolo}">
									</c:when>
									<c:otherwise>
										<input type="text" name="ruolo" id="inputRuolo" placeholder="Ruolo" class="form-control is-invalid">
										<span class="myError">${RuoloError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${EmailError == null}">
										<input type="email" name="email" id="inputEmail" placeholder="E-mail" 
											class="form-control" value="${convenzioneForm.email}">
									</c:when>
									<c:otherwise>
										<input type="email" name="email" id="inputEmail" placeholder="E-mail" class="form-control is-invalid">
										<span class="myError">${EmailError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${IndirizzoError == null}">
										<input type="text" name="indirizzo" id="inputIndirizzo" placeholder="Indirizzo" 
											class="form-control" value="${convenzioneForm.indirizzo}">
									</c:when>
									<c:otherwise>
										<input type="text" name="indirizzo" id="inputIndirizzo" placeholder="Indirizzo" class="form-control is-invalid">
										<span class="myError">${IndirizzoError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${PasswordError == null}">
										<input type="password" name="password" id="inputPassword" placeholder="Password" 
											class="form-control" value="${convenzioneForm.password}">
									</c:when>
									<c:otherwise>
										<input type="password" name="password" id="inputPassword" placeholder="Password" class="form-control is-invalid">
										<span class="myError">${PasswordError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${ConfermaPasswordError == null}">
										<input type="password" name="confermaPassword" id="inputConfermaPassword" placeholder="Conferma Password" 
											class="form-control" value="${convenzioneForm.confermaPassword}">
									</c:when>
									<c:otherwise>
										<input type="password" name="confermaPassword" id="inputConfermaPassword" placeholder="Conferma Password" class="form-control is-invalid">
										<span class="myError">${ConfermaPasswordError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="accettoDelegato" name="condizioniDelegato">
										<label class="form-check-label" for="accettoDelegato">
											Accetto
										</label>
										<a href="https://www.garanteprivacy.it/il-testo-del-regolamento" class="regolamento">Regolamento privacy Delegato</a>
								</div>
								<c:if test="${CondizioniDelegatoError != null}">
									<span class="myError">${CondizioniDelegatoError}</span>
								</c:if>
							</div>
						</div>
				
				
					</div>
					<div class ="col-6">
					<h5>Dati azienda</h5>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${RagioneSocialeError == null}">
										<input type="text" name="ragioneSociale" id="inputRagioneSociale" placeholder="Ragione sociale" 
											class="form-control" value="${convenzioneForm.ragioneSociale}">
									</c:when>
									<c:otherwise>
										<input type="text" name="ragioneSociale" id="inputRagioneSociale" placeholder="Ragione sociale" class="form-control is-invalid">
										<span class="myError">${RagioneSocialeError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${SedeError == null}">
										<input type="text" name="sede" id="inputSede" placeholder="Sede"
											class="form-control" value="${convenzioneForm.sede}">
									</c:when>
									<c:otherwise>
										<input type="text" name="sede" id="inputSede" placeholder="Sede" class="form-control is-invalid">
										<span class="myError">${SedeError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${PartitaIvaError == null}">
										<input type="text" name="pIva" id="inputPIVA" placeholder="Partita Iva" 
											class="form-control" value="${convenzioneForm.pIva}">
									</c:when>
									<c:otherwise>
										<input type="text" name="pIva" id="inputPIVA" placeholder="Partita Iva" class="form-control is-invalid">
										<span class="myError">${PartitaIvaError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${SettoreError == null}">
										<input type="text" name="settore" id="inputSettore" placeholder="Settore" 
											class="form-control" value="${convenzioneForm.settore}">
									</c:when>
									<c:otherwise>
										<input type="text" name="settore" id="inputSettore" placeholder="Settore" class="form-control is-invalid">
										<span class="myError">${SettoreError}</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<c:choose>
									<c:when test="${DescrizioneError == null}">
										<textarea name="descrizione" id="inputDescrizione" class="form-control" placeholder="Descrizione" rows="7" cols="20">${convenzioneForm.descrizione}</textarea>
									</c:when>
									<c:otherwise>
										<textarea name="descrizione" id="inputDescrizione" class="form-control is-invalid" placeholder="Descrizione" rows="7" cols="20"></textarea>
										<span class="myError">${DescrizioneError}</span>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-11">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="accettoAzienda" name="condizioniAzienda">
										<label class="form-check-label" for="accettoAzienda">
											Accetto
										</label>
									<a href="https://www.garanteprivacy.it/il-testo-del-regolamento" class="regolamento">Regolamento privacy Azienda</a>
								</div>
								<c:if test="${CondizioniAziendaError != null}">
									<span class="myError">${CondizioniAziendaError}</span>
								</c:if>
							</div>
						</div>
						<button type="submit" id="reg" class="btn btn-primary btn-block">Registrati</button>

					</div>
				</div>
            </form>
		</div>
<%@ include file="footer.jsp" %>
</body>
</html>