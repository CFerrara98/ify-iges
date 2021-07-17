<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="modal" id="modalDettagliAzienda" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header bg-dark">
				<h5 class="modal-title" style="color: #fff;">Dettagli ${AziendaPerDettagli.ragioneSociale}</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p><b>Descrizione</b> <br>${AziendaPerDettagli.descrizione}</p>
				<p><b>Partita Iva</b> <br>${AziendaPerDettagli.partitaIva}</p>
				<p><b>Email</b> <br>${DelegatoPerDettagli.email}</p>
			</div>
		</div>
	</div>


</div>

<script>
	$(window).on('load', function() {
		$('#modalDettagliAzienda').modal('show');
	});
</script>