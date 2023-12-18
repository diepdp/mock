$(document).ready(function() {
	$("#code").focus();

	$("#btn-add").on("click", function(event) {
		var resultCheck = true;
		var code = $("#code").val();
		var regexCode = /^SG\d{3}$/;
		if (code == "") {
			resultCheck = false;
			$("#code").addClass("is-invalid");
			$("#errorCode").text("Airline Code must be entered.");
		} else if (!regexCode.test(code)) {
			resultCheck = false;
			$("#code").addClass("is-invalid");
			$("#errorCode").text("Airline Code must be in the format of alpha numeric, must start with SG and 3 numbers.");
		} else {
			$("#code").removeClass("is-invalid");
			$("#errorCode").text("");
		}
		var name = $("#name").val();
		var regexName = /^[a-zA-Z0-9 ]+$/;
		if (name.trim() === "") {
			resultCheck = false;
			$("#name").addClass("is-invalid");
			$("#errorName").text("Airline Name must be entered.");
		} else if (!regexName.test(name)) {
			resultCheck = false;
			$("#name").addClass("is-invalid");
			$("#errorName").text("Airline Name must be entered.");
		} else {
			$("#name").removeClass("is-invalid");
			$("#errorName").text("");
		}
		var wholesaler = $("#wholesaler").val();
		var regexWholesaler = /^WP\d{3}$/;
		if (!regexWholesaler.test(wholesaler)) {
			resultCheck = false;
			$("#wholesaler").addClass("is-invalid");
			$("#errorWholesaler").text("Wholesaler PCC must be in the format of alpha numeric, must start with WP and 3 numbers");
		} else {
			$("#wholesaler").removeClass("is-invalid");
			$("#errorWholesaler").text("");
		}
		var number = $("#number").val();
		var regexNumber = /^\d{9}$/;
		if (number == "") {
			resultCheck = false;
			$("#number").addClass("is-invalid");
			$("#errorNumber").text("Contact Number must be entered.");
		} else if (!regexNumber.test(number)) {
			resultCheck = false;
			$("#number").addClass("is-invalid");
			$("#errorNumber").text("Contact Number must be a number and in the format of: +650123456.");
		} else {
			$("#number").removeClass("is-invalid");
			$("#errorNumber").text("");
		}
		var email = $("#email").val();
		var regexEmail = /\w+@fpt\.com$/;
		if (!regexEmail.test(email)) {
			resultCheck = false;
			$("#email").addClass("is-invalid");
			$("#errorEmail").text("Email must be in the format of abc@fpt.com.");
		} else {
			$("#email").removeClass("is-invalid");
			$("#errorEmail").text("");
		}
		if (!resultCheck) {
			event.preventDefault();
		}
	})

});