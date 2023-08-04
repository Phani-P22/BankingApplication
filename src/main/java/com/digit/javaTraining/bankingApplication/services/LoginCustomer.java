package com.digit.javaTraining.bankingApplication.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.bankingApp.Helper;
import com.digit.javaTraining.bankingApplication.beans.Customer;

@WebServlet("/login")
public class LoginCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = Helper.getConnection();
		if (conn == null) {
			return;
		}

		int customerID = Integer.parseInt(req.getParameter("customerID"));
		int pin = Integer.parseInt(req.getParameter("pin"));

		String query = "SELECT * FROM BankUser WHERE customerID = ? AND pin = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, customerID);
			ps.setInt(2, pin);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int bankID = rs.getInt("bankID");
				String bankName = rs.getString("bankName");
				String ifscCode = rs.getString("ifscCode");
				int accountNumber = rs.getInt("accountNumber");
				pin = rs.getInt("pin");
				customerID = rs.getInt("customerName");
				String customerName = rs.getString("customerName");
				int balance = rs.getInt("balance");
				String email = rs.getString("email");
				Long phoneNumber = rs.getLong("phoneNumber");

				Customer curCustomer = new Customer(bankID, bankName, ifscCode, accountNumber, pin, customerID,
						customerName, balance, email, phoneNumber);

				HttpSession session = req.getSession(true);
				session.setAttribute("curCustomer", curCustomer);

				resp.sendRedirect("/BankingApplication/home.jsp");

				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		HttpSession curSession = req.getSession();
		curSession.setAttribute("ERROR_NAME", "Login Failed");
		curSession.setAttribute("ERROR_MSG", "Login Failed! Invalid Credentials.");
		curSession.setAttribute("FILE_TO_REDIRECT", "index.html");

		resp.sendRedirect("/BankingApplication/Failure.jsp");
	}
}
