$(document).ready(function(){
	$("#logoutButton").click(function(){
		window.location.replace("LogOutControllerServlet");
		return false;
	});
	$("#confirmButton").click(function(){
		window.location.replace("TransactionControllerServlet");
		return false;
	});
	$("#cancelButton").click(function(){
		window.location.href='cardTrader.jsp';
		return false;
	});
});