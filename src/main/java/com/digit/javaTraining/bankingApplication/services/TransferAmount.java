package com.digit.javaTraining.bankingApplication.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.bankingApp.Helper;
import com.digit.javaTraining.bankingApplication.beans.Customer;

@WebServlet("/amountTransfer")
public class TransferAmount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession curSession = req.getSession();
		Connection conn = Helper.getConnection();
		if (conn == null) {
			curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed! Something Went Wrong.");
			curSession.setAttribute("ERROR_MSG", "Server Error! Please Try Later");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}
		Customer curCustomer = (Customer) curSession.getAttribute("curCustomer");
		if (curCustomer == null) {
			curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed! Something Went Wrong.");
			curSession.setAttribute("ERROR_MSG", "Invalid Customer! Session Invalid!");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		int senderCustomerID = Integer.parseInt(req.getParameter("senderCustomerID"));
		int senderAccountNumber = Integer.parseInt(req.getParameter("senderAccountNumber"));
		String senderIFSCCode = req.getParameter("senderIFSCCode");
		int amountToTransfer = Integer.parseInt(req.getParameter("amountToTransfer"));
		int receiverAccountNumber = Integer.parseInt(req.getParameter("receiverAccountNumber"));
		String receiverIFSCCode = req.getParameter("receiverIFSCCode");
		int senderPINForAuth = Integer.parseInt(req.getParameter("senderPINValidation"));

		if (curCustomer.getPin() != senderPINForAuth || curCustomer.getCustomerID() != senderCustomerID
				|| curCustomer.getAccountNumber() != senderAccountNumber
				|| !(curCustomer.getIfscCode().equals(senderIFSCCode))) {

			curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed!");
			curSession.setAttribute("ERROR_MSG", "Invalid Customer Details! You are not Authenticated!");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}
		if (curCustomer.getBalance() < amountToTransfer) {
			curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed!");
			curSession.setAttribute("ERROR_MSG", "Insufficient Funds! You do not have enough amount to Transfer");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}
		if (amountToTransfer <= 0) {
			curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed!");
			curSession.setAttribute("ERROR_MSG", "Invalid Amount! Cannot transfer " + amountToTransfer + "!");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		String query = "SELECT * FROM BankUser WHERE accountNumber = ? AND ifscCode = ?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, receiverAccountNumber);
			ps.setString(2, receiverIFSCCode);

			rs = ps.executeQuery();
			if (rs.next()) {
				ps.clearBatch();
				query = "UPDATE BankUser SET balance = balance - ? where accountNumber = ? and ifscCode = ?";
				ps = conn.prepareStatement(query);
				ps.setInt(1, amountToTransfer);
				ps.setInt(2, curCustomer.getAccountNumber());
				ps.setString(3, senderIFSCCode);

				int statusCode = ps.executeUpdate();
				if (statusCode > 0) {
					query = "UPDATE BankUser SET balance = balance + ? WHERE accountNumber = ? and ifscCode = ?";
					ps.clearBatch();
					ps = conn.prepareStatement(query);
					ps.setInt(1, amountToTransfer);
					ps.setInt(2, receiverAccountNumber);
					ps.setString(3, receiverIFSCCode);

					statusCode = ps.executeUpdate();
					if (statusCode > 0) {
						ps.clearBatch();
						Long transactionID = 0L;
						while (true) {
							transactionID = (long) (Math.random() * (999999999999999L - 100000000000000L)
									+ 100000000000000L);
							query = "SELECT * FROM transactions WHERE transactionID = ?";
							ps = conn.prepareStatement(query);
							ps.setLong(1, transactionID);

							rs = ps.executeQuery();
							if (rs.next()) {
								continue;
							}
							break;
						}

						query = "INSERT INTO transactions VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
						ps.clearBatch();

						ps = conn.prepareStatement(query);
						ps.setInt(1, curCustomer.getCustomerID());
						ps.setString(2, curCustomer.getBankName());
						ps.setString(3, senderIFSCCode);
						ps.setInt(4, senderAccountNumber);
						ps.setString(5, receiverIFSCCode);
						ps.setInt(6, receiverAccountNumber);
						ps.setInt(7, amountToTransfer);
						ps.setLong(8, transactionID);

						statusCode = ps.executeUpdate();
						if (statusCode > 0) {

							curSession.setAttribute("SUCCESS_NAME", "Transaction Successful!");
							curSession.setAttribute("SUCCESS_MSG", "Amount Transfered Successfully!");
							curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

							resp.sendRedirect("/BankingApplication/Success.jsp");
							return;
						} else {
							throw new Exception();
						}
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		curSession.setAttribute("ERROR_NAME", "Transfer Amount Failed!");
		curSession.setAttribute("ERROR_MSG", "Transfer Fail!");
		curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

		resp.sendRedirect("/BankingApplication/Failure.jsp");
	}
}
