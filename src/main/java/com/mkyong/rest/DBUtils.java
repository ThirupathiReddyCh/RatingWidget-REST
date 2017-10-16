package com.mkyong.rest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {

	static ResourceBundle rb = ResourceBundle.getBundle("config");
	static String driver =rb.getString("db.driver");
	static String username = rb.getString("db.username");
	static String password = rb.getString("db.password");
	static String url=rb.getString("db.url");

	public static Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url,username, password);
		} catch (Exception e) {
			System.out.println("In getDBConnection method catch block "+ e);
		}
		return connection;
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception ex) {
			System.out.println("In closeResultSet " + ex);
		}
	}

	public static void closeStatement(Statement st) {
		try {
			st.close();
		} catch (Exception ex) {
			System.out.println("In closeStatement " + ex);
		}
	}

	public static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
			System.out.println("In closeConnection " + ex);
		}
	}

}
