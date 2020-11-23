package ua.itea.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class Config {
	private Locale locale;
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void save() {
		try(DataOutputStream os = getOutputStream(getFile());) {
			saveLocale(os);	
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DataOutputStream getOutputStream(File file) throws FileNotFoundException {
		return new DataOutputStream(new FileOutputStream(file));

	}
	
	private void saveLocale(DataOutputStream os) throws IOException {
		os.writeUTF(getLocale().getLanguage());
		os.writeUTF(getLocale().getCountry());
	}

	public void load() {
		try(DataInputStream os = getInputStream(getFile());) {
			loadLocale(os);	
		} catch (IOException e) {
			locale = LocaleFlag.getSuitableLocale(Locale.getDefault());
		}
	}
	
	private DataInputStream getInputStream(File file) throws FileNotFoundException {
		return new DataInputStream(new FileInputStream(file));
	}

	private void loadLocale(DataInputStream is) throws IOException {
		locale = new Locale(is.readUTF(), is.readUTF());
	}
	
	private File getFile() throws IOException {
		File file = new File("config");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		return file;
	}
}
