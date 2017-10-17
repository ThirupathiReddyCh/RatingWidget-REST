package com.techsophy;
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
			if(rs!=null){
			rs.close();}
		} catch (Exception ex) {
			System.out.println("In closeResultSet " + ex);
		}
	}

	public static void closeStatement(Statement st) {
		try {
			if(st!=null){
				st.close();}		
		} catch (Exception ex) {
			System.out.println("In closeStatement " + ex);
		}
	}

	public static void closeConnection(Connection con) {
		try {
			if(con!=null){
			con.close();}
		} catch (Exception ex) {
			System.out.println("In closeConnection " + ex);
		}
	}

}
