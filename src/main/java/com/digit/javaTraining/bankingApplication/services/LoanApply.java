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
import com.digit.javaTraining.bankingApplication.beans.Loan;

@WebServlet("/loan")
public class LoanApply extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String typeOfLoan = req.getParameter("typeOfLoan");

		Connection conn = Helper.getConnection();
		HttpSession curSession = req.getSession();
		if (conn == null) {
			return;
		}

		String query = "SELECT * FROM Loan WHERE loanType = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, typeOfLoan);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int loanID = rs.getInt("loanID");
				String loanType = rs.getString("loanType");
				int tenure = rs.getInt("tenure");
				float intrest = rs.getFloat("intrest");
				String description = rs.getString("description");

				Loan curLoanObject = new Loan(loanID, loanType, tenure, intrest, description);

				curSession.setAttribute("curLoan", curLoanObject);

				resp.sendRedirect("/BankingApplication/LoanDetails.jsp");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		curSession.setAttribute("ERROR_NAME", "Loan Application Failed");
		curSession.setAttribute("ERROR_MSG", "Loan Application Failed! Something Went Wrong.");
		curSession.setAttribute("FILE_TO_REDIRECT", "home.jsp");

		resp.sendRedirect("/BankingApplication/Failure.jsp");
		return;
	}
}
