package ua.itea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import ua.itea.Configuration;

public class DBConnector {
	private Configuration config;
	
	public DBConnector(Configuration config) {
		this.config = config;
	}
	
	public Configuration getConfiguration() {
		return config;
	}
	
	public Connection getConnection() throws SQLException {
		try {
			return createConnectionToDatabase();
		} catch (SQLSyntaxErrorException e) {
			Connection conn = createConnectionToHost();
			
			try (Statement statement = conn.createStatement();) {
				statement.execute("CREATE DATABASE IF NOT EXISTS `" + config.getDatabase() + "` "
								  + "DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;");
				statement.execute("USE `" + config.getDatabase() + "`;");
			}
			return conn;
		}
	}
	
	private Connection createConnectionToDatabase() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + config.getHost() + "/"
				   						   + config.getDatabase() + "?",
				   						   config.getLogin(), config.getPassword());
	}
	
	private Connection createConnectionToHost() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + config.getHost() + "?",
				   						   config.getLogin(), config.getPassword());
	}
}
