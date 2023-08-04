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
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-image: url("images/image.jpg");
	background-size: cover;
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
	background-color: rgba(255, 255, 255, 0.7);
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	text-align: center;
}

form {
	margin-top: 20px;
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
}

input[type="text"], input[type="number"], input[type="password"] {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
	width: 100%;
	margin-top: 3px;
	margin-bottom: 3px;
}

input[type="submit"] {
	background-color: #3366cc;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 4px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.4s ease;
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
		<form action="amountTransfer" method="post">
			<label for="cust_id">Your Customer ID:</label> <input type="number"
				id="senderCustomerID" name="senderCustomerID"> <label
				for="senderAccountNumber">Your Account Number:</label> <input
				type="number" id="senderAccountNumber" name="senderAccountNumber">
			<label for="IFSC">Your IFSC:</label> <input type="number"
				id="senderIFSCCode" name="senderIFSCCode"> <label
				for="amountToTransfer">Enter Amount to Transfer:</label> <input
				type="text" id="amountToTransfer" name="amountToTransfer"> <label
				for="receiver_accno">Enter Receiver Account Number:</label> <input
				type="text" id="receiverAccountNumber" name="receiverAccountNumber">
			<label for="receiverIFSCCode">Receivers IFSC:</label> <input
				type="text" id="receiverIFSCCode" name="receiverIFSCCode"> <label
				for="pin">Enter Your PIN:</label> <input type="password"
				id="senderPINValidation" name="senderPINValidation"> <input
				type="submit" value="Submit">
		</form>
	</div>



</body>
</html>