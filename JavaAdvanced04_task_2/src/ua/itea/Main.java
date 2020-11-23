package ua.itea;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	
	public static void main(String[] args) {
		URL url = Main.class.getClassLoader().getResource("data/file");
		
		System.out.println("source: \"" + url.getPath() + "\"");
		try (InputStream is = url.openStream();) {
			
			int c;
			while((c = is.read()) != -1) {
				System.out.write(c);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/***********************************************************/
		Path jarPath = Paths.get(url.getPath()).getParent().getParent().getParent();
		String strPath = jarPath.toString().substring(jarPath.toString().indexOf(":") + 1) + "/data/file";
		
		Path path = Paths.get(strPath);
		
		System.out.println("source: \"" + path + "\"");
		try {
			Files.copy(path, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

