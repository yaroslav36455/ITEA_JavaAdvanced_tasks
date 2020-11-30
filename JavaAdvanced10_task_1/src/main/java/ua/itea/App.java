package ua.itea;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class App {
    public static void main(String[] args) {
    	SAXParserFactory spf = SAXParserFactory.newInstance();
    	
    	try {
			SAXParser sp = spf.newSAXParser();
			POMParser pomParser = new POMParser();
			
			sp.parse(App.class.getClassLoader().getResourceAsStream("pom.xml"), pomParser);
			System.out.println(pomParser.getProject());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
    }
}
