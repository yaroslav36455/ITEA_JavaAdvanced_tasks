package ua.itea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private final static String FIND = "SELECT * FROM `users` WHERE `login`=? AND `password`=?";
	
	public boolean isPresent(Connection conn, String login, String password) throws SQLException {
		boolean found = false;
		
		try (PreparedStatement ps = conn.prepareStatement(FIND);) {
			
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			found = rs.next();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conn.close();
		return found;
	}
}
