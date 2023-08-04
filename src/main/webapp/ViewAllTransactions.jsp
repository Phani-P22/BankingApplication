<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.javaTraining.bankingApplication.beans.Customer, java.util.ArrayList,
	com.digit.javaTraining.bankingApplication.beans.Transaction"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Transactions</title>
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
	max-width: 900px;
	padding: 30px;
	background-color: rgba(255, 255, 255, 0.7);
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 15px;
	text-align: center;
	border: 1px solid #ccc;
}

th {
	background-color: #3366cc;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a {
	display: block;
	margin-top: 20px;
	text-align: center;
	color: #3366cc;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s;
}

a:hover {
	color: #005580;
}

tr.green {
	background-color: #c8e6c9;
}

tr.red {
	background-color: #ffcdd2;
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
	
	String isListGenerated = (String) session.getAttribute("isListGenerated");
	if (isListGenerated == null) {
		out.println("GONE");
		response.sendRedirect("/BankingApplication/viewAllTransactions");
		return;
	} else {
		session.removeAttribute("isListGenerated");
	}

	ArrayList<Transaction> arrayList = (ArrayList<Transaction>) session.getAttribute("ALL_TRANSACTIONS");
	if (arrayList == null) {
		return;
	}
	%>


	<div class="container">
		<%
		if (arrayList.size() == 0) {
		%>
		<h1 align="center">No Transactions Made Yet!</h1>
		<%
		return;
		}
		%>
		<h1 align="center">All Transactions</h1>
		<br>
		<table>
			<tr>
				<th>Customer ID</th>
				<th>Sender Bank Name</th>
				<th>Sender Bank IFSC Code</th>
				<th>Sender Account Number</th>
				<th>Receiver IFSC</th>
				<th>Receiver Account Number</th>
				<th>Amount of Transfer</th>
				<th>Transaction ID</th>
			</tr>

			<%
			for (Transaction curTransaction : arrayList) {
				String trTypeClass = curTransaction.getSenderAccountNumber() == curCustomer.getAccountNumber() ? "red" : "green";
			%>
			<tr class="<%=trTypeClass%>">
				<td><%=curTransaction.getCustomerID()%></td>
				<td><%=curTransaction.getSenderBankName()%></td>
				<td><%=curTransaction.getSenderIFSC()%></td>
				<td><%=curTransaction.getSenderAccountNumber()%></td>
				<td><%=curTransaction.getReceiverIFSC()%></td>
				<td><%=curTransaction.getReceiverAccountNumber()%></td>
				<td><%=curTransaction.getAmountOfTransfer()%></td>
				<td><%=curTransaction.getTransactionID()%></td>
			</tr>
			<%
			}
			%>

		</table>
		<a href="home.jsp">Go to Home Page</a>
	</div>

</body>