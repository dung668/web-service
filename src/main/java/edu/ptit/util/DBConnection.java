package edu.ptit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static String URL = DatabaseProperties.getData("url");
	public static String USER = DatabaseProperties.getData("user");
	public static String PASSWORD = DatabaseProperties.getData("password");
	public static String DRIVER = DatabaseProperties.getData("driver");
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
