<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
	<title>Assgn 03 Login Page</title>
	<link rel="stylesheet" type="text/css" href="styles/main.css"/>
</head>
<body>
	<div class="centerAlign">
	<h1>Card Trading Post</h1>
	<p><%= bean.getMessage()  %></p>
	<div class="stringInput">
		<span class="string">Username:</span><input type="text" name="username" id="username" />
	</div>
	<div class="stringInput">
		<span class="string">Password:</span><input type="text" name="password" id="password" />
	</div>
	<label><button id="login">Login</button><span class="br"></span></label>
	<label><button id="registration">Registration</button></label>
	</div>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="script/login.js"></script>
	<script src="script/redirect.js" ></script>
</body>
</html>

<% bean.setMessage("");  %>