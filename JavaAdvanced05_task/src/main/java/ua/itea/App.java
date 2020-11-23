package ua.itea;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
    	try {
    		new Window();	
		} catch (IOException e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
}
