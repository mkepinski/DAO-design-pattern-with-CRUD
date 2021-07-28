package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

	private static final String user = "root";
	private static final String url = "jdbc:mysql://localhost:3306/DAO_db?useSSL=false&characterEncoding=utf8";
	private static final String password = "ZpQmLa01";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
}
