$(document).ready(function(){
	$("#registration").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var confirm = $("#confirm").val();
		
		$.redirect('RegistrationControllerServlet', {username: username, password: password, confirm: confirm});
		return false;
	});

	$("#backToLogin").click(function(){
		window.location.replace("login.jsp");
		return false;
	});
	
});