$(document).ready(function(){
	$("#registration").click(function(){
		$.redirect('registration.jsp');
	});

	$("#login").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		
		$.redirect('LoginControllerServlet', {username: username, password: password});	
	});
});