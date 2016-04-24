$(document).ready(function() {
	$("#alert-error").hide();
	if (window.location.search == "?error=true") {
		showAlert();
	}
	function showAlert() {
		$("#alert-error").alert();
		$("#alert-error").fadeTo(2000, 500).slideUp(500, function() {
			$("#alert-errorr").alert('close');
		});
	}
	;
});