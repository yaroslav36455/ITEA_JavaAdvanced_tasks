package ua.itea.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static final String DATABASE = getDatabasePath();

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE);
	}
	
	private static String getDatabasePath() {
    	String database = new File("users.db").getAbsolutePath();

    	database = "jdbc:sqlite:" + database.substring(database.lastIndexOf(':') + 1);
    	return database;
	}
}
