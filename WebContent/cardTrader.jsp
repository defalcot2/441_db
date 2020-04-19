<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />

<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><%= bean.getUserName()  %> Trading Page</title>
<link rel="stylesheet" type="text/css" href="styles/servlets.css"/>
</head>
<body>
<h1>Current Card Collection for <%= bean.getUserName()  %></h1>
<p><%= bean.getMessage()  %></p>
<% if(!utilities.DataTools.NullOrEmpty(bean.getCardCollectionHTML())) { %>
	<table style="width:100%" id="cardtrading">
		<tr>
	       <th>Card</th>
	       <th>Card Number</th>
	       <th>Value</th>
	       <th>Amount Owned</th>
	       <th>Total Value</th>
	       <th>Action</th>
	       <th>Quantity</th>
	       <th></th>
	    </tr>
		<%= bean.getCardCollectionHTML()  %>
	</table>
<% } else out.println("<h3>You currently do not own any cards<h3>"); %>
<h1>Available Cards to Buy</h1>
<% if(!utilities.DataTools.NullOrEmpty(bean.getCardsForSaleHTML())) { %>
	<table style="width:100%" id="cardtrading">
		    <tr>
		       <th>Card</th>
		       <th>Card Number</th>
		       <th>Cost</th>
		       <th>Quantity</th>
		       <th></th>
		    </tr>
		<%= bean.getCardsForSaleHTML()  %>
	</table>
<% } else out.println("<h3>You do not have any cards left to buy<h3>"); %>
<hr/>

	<div class="input-right">
		<button id="logoutButton">Logout</button>
	</div>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
	<script src="script/redirect.js" type="text/javascript"></script>
	<script src="script/cardTrader.js" type="text/javascript"></script>
</body>
</html>

<% bean.setMessage("");  %>