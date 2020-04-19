<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Assgn 02 Registration Page</title>
	<link rel="stylesheet" type="text/css" href="styles/main.css"/>
</head>
<body>
	<div class="centerAlign">
	<h1>Trader Registration</h1>
	<div style="form-style">
	<p><%= bean.getMessage()  %></p>
	<div class="stringInput">
		<span class="string">Username:</span><input type="text" name="username" id="username" />
	</div>
	<div class="stringInput">
		<span class="string">Password:</span><input type="text" name="password" id="password" />
	</div>
	<div class="stringInput">
		<span class="string">Confirm:</span><input type="text" name="confirm" id="confirm" />
	</div>
	<label><button id="registration">Register</button><span class="br"></span></label>
	<label><button id="backToLogin">Back to Login</button></label>
	</div>
	</div>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="script/registration.js"></script>
    <script src="script/redirect.js"></script>
</body>
</html>

<% bean.setMessage("");  %>