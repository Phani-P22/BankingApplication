package com.digit.javaTraining.bankingApplication.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.bankingApp.Helper;

@WebServlet("/register")
public class RegisterCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bankID = Integer.parseInt(req.getParameter("bankID"));
		String bankName = req.getParameter("bankName");
		String ifscCode = req.getParameter("ifscCode");
		int accountNumber = Integer.parseInt(req.getParameter("accountNumber"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int customerId = Integer.parseInt(req.getParameter("customerID"));
		String customerName = req.getParameter("customerName");
		int balance = Integer.parseInt(req.getParameter("balance"));
		String email = req.getParameter("email");
		Long phoneNumber = Long.parseLong(req.getParameter("phoneNumber"));

		Connection conn = Helper.getConnection();
		HttpSession curSession = req.getSession();
		if (conn == null) {
			curSession.setAttribute("ERROR_NAME", "Registration Failed");
			curSession.setAttribute("ERROR_MSG", "Registration Failed! Something Went Wrong.");
			curSession.setAttribute("FILE_TO_REDIRECT", "index.html");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		String query = "INSERT INTO BankUser VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, bankID);
			ps.setString(2, bankName);
			ps.setString(3, ifscCode);
			ps.setInt(4, accountNumber);
			ps.setInt(5, pin);
			ps.setInt(6, customerId);
			ps.setString(7, customerName);
			ps.setInt(8, balance);
			ps.setString(9, email);
			ps.setLong(10, phoneNumber);

			int statusCode = ps.executeUpdate();
			if (statusCode > 0) {

				curSession.setAttribute("SUCCESS_NAME", "Registration Successful");
				curSession.setAttribute("SUCCESS_MSG", "Registration Done Successfully!");
				curSession.setAttribute("FILE_TO_REDIRECT", "index.html");

				resp.sendRedirect("/BankingApplication/Success.jsp");
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		curSession.setAttribute("ERROR_NAME", "Registration Failed");
		curSession.setAttribute("ERROR_MSG", "Registration Failed! Something Went Wrong.");
		curSession.setAttribute("FILE_TO_REDIRECT", "index.html");

		resp.sendRedirect("/BankingApplication/Failure.jsp");
	}
}
