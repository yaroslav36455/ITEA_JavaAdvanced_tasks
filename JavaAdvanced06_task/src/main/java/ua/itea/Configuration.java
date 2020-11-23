package ua.itea;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Configuration {
	private String source;
	private String host;
	private String database;
	private String login;
	private String password;
	
	public Configuration(String source) throws IOException {
		setSource(source);
		update();
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}

	public String getHost() {
		return host;
	}

	public String getDatabase() {
		return database;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void update() throws IOException {
		FileInputStream fis = new FileInputStream(Paths.get(source + ".properties").toFile());
		ResourceBundle resourceBundle = new PropertyResourceBundle(fis);
		
    	host = resourceBundle.getString("host");
		database = resourceBundle.getString("database");
		login = resourceBundle.getString("login");
		password = resourceBundle.getString("password");
	}
}
