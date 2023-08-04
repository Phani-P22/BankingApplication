package com.digit.javaTraining.bankingApplication.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.bankingApp.Helper;
import com.digit.javaTraining.bankingApplication.beans.Customer;

@WebServlet("/changePassword")
public class ChangePIN extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession curSession = null;
		Customer curCustomer = null;
		try {
			curSession = req.getSession();
			curCustomer = (Customer) curSession.getAttribute("curCustomer");
			if (curSession == null || curCustomer == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			curSession.setAttribute("ERROR_NAME", "PIN Change Failed");
			curSession.setAttribute("ERROR_MSG", "PIN Change Failed! Something Went Wrong.");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		Connection conn = Helper.getConnection();
		int oldPIN = Integer.parseInt(req.getParameter("oldPIN"));
		int newPIN = Integer.parseInt(req.getParameter("newPIN"));
		int reEnteredNewPIN = Integer.parseInt(req.getParameter("reEnterNewPIN"));
		if (conn == null || newPIN != reEnteredNewPIN || curCustomer.getPin() != oldPIN) {
			curSession.setAttribute("ERROR_NAME", "PIN Change Failed");
			curSession.setAttribute("ERROR_MSG", "PIN Change Failed! Something Went Wrong.");
			curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

			resp.sendRedirect("/BankingApplication/Failure.jsp");
			return;
		}

		String query = "UPDATE BankUser SET pin = ? WHERE customerID = ? AND accountNumber = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, newPIN);
			ps.setInt(2, curCustomer.getCustomerID());
			ps.setInt(3, curCustomer.getAccountNumber());

			int statusCode = ps.executeUpdate();
			if (statusCode > 0) {

				curCustomer.setPin(newPIN);
				curSession.setAttribute("curCustomer", curCustomer);

				curSession.setAttribute("SUCCESS_NAME", "Success");
				curSession.setAttribute("SUCCESS_MSG", "Pin Changed Successfully.");
				curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

				resp.sendRedirect("/BankingApplication/Success.jsp");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		curSession.setAttribute("ERROR_NAME", "PIN Change Failed");
		curSession.setAttribute("ERROR_MSG", "PIN Change Failed! Something Went Wrong.");
		curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

		resp.sendRedirect("/BankingApplication/Failure.jsp");
	}
}
