<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.javaTraining.bankingApplication.beans.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
	background-image: url("images/image.jpg");
	background-size: cover;
	background-position: center;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-repeat: no-repeat;
	position: relative;
}

body::before {
	content: "";
	background-image: inherit;
	background-size: cover;
	background-repeat: no-repeat;
	filter: blur(2px);
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: -1;
}

.container {
	max-width: 400px;
	padding: 30px;
	padding-right: 50px;
	background-color: rgba(255, 255, 255, 0.7);
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h4, h1 {
	color: #3366cc;
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 8px;
	color: #555;
}

input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

input[type="submit"] {
	background-color: #3366cc;
	color: #fff;
	font-weight: bold;
	cursor: pointer;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #005580;
}
</style>
</head>
<body>

	<%
	session = request.getSession();
	if (session == null) {
		response.sendRedirect("/BankingApplication/index.html");
		return;
	}

	Customer curCustomer = (Customer) session.getAttribute("curCustomer");
	if (curCustomer == null) {
		response.sendRedirect("/BankingApplication/index.html");
		return;
	}
	%>

	<div class="container">
		<h1>PIN Update Page</h1>
		<form action="changePassword" method="post">

			<label>Old PIN</label> <input type="password" name="oldPIN" required>

			<br> <label>New PIN</label> <input type="password" name="newPIN"
				required> <br> <label>Confirm PIN</label> <input
				type="password" name="reEnterNewPIN" required> <input
				type="submit" value="Update PIN">
		</form>
	</div>

</body>
</html>