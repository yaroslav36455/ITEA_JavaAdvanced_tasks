package ua.itea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ua.itea.Arg;

public class Database {
	public final static int MAX_KEY_LENGTH = 15;
	public final static int MAX_VALUE_LENGTH = 50;
	private final static String INSERTER = "INSERT INTO `arguments` (`key`, `value`) VALUES (?,?);";
	private final static String READER = "SELECT `id`, `key`, `value` FROM `arguments`";
	private final static String PROVIDE_TABLE = "CREATE TABLE IF NOT EXISTS `arguments`"
			+ " (`id` INT PRIMARY KEY AUTO_INCREMENT,"
			+ " `key` VARCHAR(" + MAX_KEY_LENGTH + ") DEFAULT NULL,"
			+ " `value` VARCHAR(" + MAX_VALUE_LENGTH + ") DEFAULT NULL);";
	
	public Database(Connection conn) throws SQLException {
		Statement statement = conn.createStatement();
		
		statement.execute(PROVIDE_TABLE);
		statement.close();
	}
	
	public void insert(Connection conn, Iterable<Arg> args) throws SQLException {
		PreparedStatement inserter = conn.prepareStatement(INSERTER);
		
		for (Arg arg : args) {
			inserter.setString(1, arg.getKey());
			inserter.setString(2, arg.getValue());
			inserter.execute();
		}
		
		inserter.close();
	}

	public Iterable<TableRow<Arg>> read(Connection connection) throws SQLException {
		ArrayList<TableRow<Arg>> array = new ArrayList<>();
		PreparedStatement reader = connection.prepareStatement(READER);
		ResultSet resultSet = reader.executeQuery();
		
		while(resultSet.next()) {
			Arg newArg = new Arg();
			
			newArg.setKey(resultSet.getString("key"));
			newArg.setValue(resultSet.getString("value"));
			
			array.add(new TableRow<Arg>(resultSet.getInt("id"), newArg));
		}
		
		resultSet.close();
		reader.close();
		
		return array;
	}
}
