<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.digit.javaTraining.bankingApplication.beans.*"%>
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
	max-width: 800px;
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
</style>
</head>
<body>

	<%
	session = request.getSession();
	Customer curBankUser = (Customer) session.getAttribute("curCustomer");
	Loan curLoan = (Loan) session.getAttribute("curLoan");
	%>


	<div class="container">
		<h1 align="center">Applied Loan Details</h1>
		<table>
			<tr>
				<th>Loan ID</th>
				<th>Loan Type</th>
				<th>Tenure</th>
				<th>Interest</th>
				<th>Description</th>
			</tr>
			<tr>
				<td><%=curLoan.getLoanID()%></td>
				<td><%=curLoan.getLoanType()%></td>
				<td><%=curLoan.getTenure()%></td>
				<td><%=curLoan.getIntrest()%></td>
				<td><%=curLoan.getDescription()%></td>
			</tr>
		</table>
		<a href="home.jsp">Go Back</a>
	</div>

</body>
</html>