$(document).ready(function(){
	$("#logoutButton").click(function(){
		window.location.replace("LogOutControllerServlet");
		return false;
	});
});