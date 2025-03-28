package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConectController {
	public static void main(String[] args) {
		try {
			String user = "sa";
			String pass = "123";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=ASS_SOF3021;encrypt=false";
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from Accounts";
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Username: " + rs.getString(1));
				System.out.println("Password: " + rs.getString(2));
				System.out.println("Fullname: " + rs.getString(3));
				System.out.println("Email: " + rs.getString(4));
				
				System.out.println("\n");

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
