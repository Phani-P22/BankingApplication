<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.javaTraining.bankingApplication.beans.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Banking Application Home</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-position: center;
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

h1 {
	color: #3366cc;
	margin-bottom: 20px;
}

p {
	margin-bottom: 20px;
}

a {
	display: block;
	margin: 10px 0;
	padding: 10px 20px;
	background-color: #3366cc;
	color: #fff;
	text-decoration: none;
	border-radius: 4px;
	font-weight: bold;
	transition: background-color 0.4s ease;
}

a:hover {
	background-color: #005580;
}
</style>
</head>
<body>

	<div align="center">
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
	</div>

	<br>
	<br>


	<div class="container">
		<h1>
			Welcome to the Bank!
			<%=curCustomer.getBankName()%></h1>
		<br> <br> <a href="CheckBalance.jsp">Check Balance</a> <a
			href="ChangePIN.jsp">Change PIN</a> <a href="TransferAmount.jsp">Transfer
			Amount</a> <a href="ViewAllTransactions.jsp">View All Transactions</a> <a
			href="Loan.jsp">Apply Loan</a> <a href="logout">LogOut</a>

	</div>


</body>
</html>