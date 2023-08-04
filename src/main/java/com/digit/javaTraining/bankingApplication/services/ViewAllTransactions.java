package com.digit.javaTraining.bankingApplication.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.bankingApp.Helper;
import com.digit.javaTraining.bankingApplication.beans.Customer;
import com.digit.javaTraining.bankingApplication.beans.Transaction;

@WebServlet("/viewAllTransactions")
public class ViewAllTransactions extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession curSession = req.getSession();
		Connection conn = Helper.getConnection();
		if (conn == null) {
			curSession.setAttribute("ERROR_NAME", "Server Error");
			curSession.setAttribute("ERROR_MSG", "Server Error! Please Try Later");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		Customer curCustomer = (Customer) curSession.getAttribute("curCustomer");
		if (curCustomer == null) {
			curSession.setAttribute("ERROR_NAME", "View Transactions Failed");
			curSession.setAttribute("ERROR_MSG", "Invalid Customer! Session Invalid!");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		ArrayList<Transaction> receiveTransactionsList = new ArrayList<Transaction>();
		try {
			String query = "SELECT * FROM transactions WHERE senderAccountNumber = ? OR receiverAccountNumber = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, curCustomer.getAccountNumber());
			ps.setInt(2, curCustomer.getAccountNumber());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int customerID = rs.getInt("customerID");
				String senderBankName = rs.getString("senderBankName");
				String senderIFSC = rs.getString("senderIFSC");
				int senderAccountNumber = rs.getInt("senderAccountNumber");
				String receiverIFSC = rs.getString("receiverIFSC");
				int receiverAccountNumber = rs.getInt("receiverAccountNumber");
				int amountOfTransfer = rs.getInt("amountOfTransfer");
				Long transactionID = rs.getLong("transactionID");

				Transaction curTransaction = new Transaction(customerID, senderBankName, senderIFSC,
						senderAccountNumber, receiverIFSC, receiverAccountNumber, amountOfTransfer, transactionID);

				receiveTransactionsList.add(curTransaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
			curSession.setAttribute("ERROR_NAME", "Server Error");
			curSession.setAttribute("ERROR_MSG", "Server Error! Please Try Later");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");
			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		curSession.setAttribute("ALL_TRANSACTIONS", receiveTransactionsList);
		curSession.setAttribute("isListGenerated", "true");
//		curSession.setAttribute("CHECK", "Checked!");
		resp.sendRedirect("/BankingApplication/ViewAllTransactions.jsp");
	}

}
