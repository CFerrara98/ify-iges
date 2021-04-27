<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<div class="modal" id="myModal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Info</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="font-size: 18px; height: 100px;">${message}</div>

		</div>
	</div>


</div>

<script>
	$(window).on('load', function() {
		$('#myModal').modal('show');
	});
</script>