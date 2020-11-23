package ua.itea;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String FILE_NAME = "data/textfile";
	private static final String ICON_NAME = "data/dog.jpg";
	
	public Window() throws IOException {
		
		setLayout(new GridLayout(2, 2));
		add(new JLabel(getExternallImageIcon()));
		add(new JLabel(getExternalFileText()));
		add(new JLabel(getInternalImageIcon()));
		add(new JLabel(getInternalFileText()));
		
		pack();
		setLocationByPlatform(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private ImageIcon getExternallImageIcon() {
		return new ImageIcon(ICON_NAME);
	}
	
	private ImageIcon getInternalImageIcon() {
		URL url = this.getClass().getClassLoader().getResource(ICON_NAME);
		
		return new ImageIcon(url);
	}

	private String getExternalFileText() throws IOException {
		return new String(Files.readAllBytes(new File(FILE_NAME).toPath()));
	}
	
	private String getInternalFileText() throws IOException {
		StringBuilder sb = new StringBuilder();
		
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
			String tmp;
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp);
			}
		}
		
		return sb.toString();
	}
}
