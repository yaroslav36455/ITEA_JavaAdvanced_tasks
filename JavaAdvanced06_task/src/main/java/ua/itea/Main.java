package ua.itea;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import com.mysql.jdbc.MysqlDataTruncation;

import ua.itea.db.DBConnector;
import ua.itea.db.Database;
import ua.itea.db.TableRow;

public class Main {
	public static void main(String[] args) {
		AppOptions appOptions = new AppOptions();
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cl = parser.parse(appOptions.getOptions(), args);
			Arguments arguments = new Arguments(appOptions.getKeys(), cl);
			DBConnector connector = new DBConnector(new Configuration("Config"));

			try (Connection connection = connector.getConnection();) {
				Database database = new Database(connection);

				database.insert(connection, arguments);
			}

		} catch(MysqlDataTruncation e) {
			System.out.println("ArgumentException: value length more than "
							   + Database.MAX_VALUE_LENGTH + " characters");
		} 
		catch(ParseException e) {
			System.out.println(e.getLocalizedMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("[OPTION...]", "Options:", appOptions.getOptions(), "\n");
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/**********************************************************************/
		
		System.out.println("Database content:");
		try {
			DBConnector connector = new DBConnector(new Configuration("Config"));

			try (Connection connection = connector.getConnection();) {
				Database database = new Database(connection);
				Iterable<TableRow<Arg>> content;

				content = database.read(connection);
				for (TableRow<Arg> row : content) {
					int id = row.getId();
					String key = row.getData().getKey();
					String value = row.getData().getValue();

					System.out.println(String.format("%3d|%-20s|%s", id, key, (value == null) ? "" : value));
				}
			}

		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
