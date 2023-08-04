package com.digit.javaTraining.bankingApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Helper {

	private static Connection conn;

	public static Connection getConnection() {
		if (conn == null) {
			String url = "jdbc:mysql://localhost:3306/BankingApplication";
			String user = "root";
			String pwd = "Phani@123";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

}
