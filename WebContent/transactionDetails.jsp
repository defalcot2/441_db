<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />
<%@page import="utilities.DataTools"%>

<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Details</title>
<link rel="stylesheet" type="text/css" href="styles/servlets.css"/>
</head>
<body>
	<h1>Transaction Details</h1>
	<p><%= bean.getMessage()  %></p>
	<table style="width:100%" id="transaction">
		<tr><th>Card</th>
		<th>Number</th>
		<th>Buy/Sell</th>
		<th>Quantity</th>
		<th>Card Price</th>
		<th>Total Value</th></tr>
			<tr><td><%= bean.getCurrentCard()[0] %></td>
				<td><%= bean.getCurrentCard()[1] %></td>
				<td><%= bean.getCurrentCard()[5] %></td>
				<td><%= bean.getCurrentCard()[2] %></td>
				<td>$<%= bean.getCurrentCard()[4] %></td>
				<td>$<%= bean.getCurrentCard()[6] %></td>
			</tr>
		<tr><th colspan="6" style="text-align:left">Bank Account Information</th></tr>
		<tr><td>Account Holder Name</td>
			<td colspan="5"><input size="50" type="text" name="accountHolderName" /></td></tr>
		<tr><td>Routing Number</td>
			<td colspan="5"><input size="50" type="text" name="routingNumber" /></td></tr>
	</table>
	<hr/>
	<div class="input-left">
		<button id="confirmButton" onclick="this.disabled=true">Confirm Transaction</button>
		<button id="cancelButton">Cancel</button>
	</div>
	<div class="input-right">
		<button id="logoutButton" class="logoutButton">Logout</button>
	</div>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="script/redirect.js"></script>
	<script src="script/transactionDetails.js"></script>
	
	<% bean.setMessage(""); %>
</body>
</html>