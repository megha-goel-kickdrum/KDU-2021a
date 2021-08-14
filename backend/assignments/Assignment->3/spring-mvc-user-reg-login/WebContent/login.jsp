<%@ page isELIgnored="false"%>
<html>
<head>
<title>User registration and login</title>
</head>
<body>

	<h3>User registration and login </h3>


	<form action="login" method="post">
		<pre>
		 <strong>Login Here | <a href="registration.jsp">Click here to Register</a></strong>
		
		User Id: <input type="text" name="userId" />
	
		Password: <input type="password" name="password" />

		<input type="submit" value="Login" />
		</pre>
	</form>

	${msg}

</body>
</html>