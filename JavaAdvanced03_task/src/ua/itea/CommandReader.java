package ua.itea;

import java.io.InputStream;
import java.util.Scanner;

public class CommandReader {
	private Scanner scanner;
	private String commandName;
	private String[] commandArgs;
	
	public CommandReader(InputStream is) {
		scanner = new Scanner(is);
	}
	
	public void read() {
		String[] words = scanner.nextLine().split("[\\p{javaWhitespace}]+");
		
		commandName = words[0];
		commandArgs = new String[words.length - 1];
		for (int i = 1; i < words.length; i++) {
			commandArgs[i - 1] = words[i];
		}
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public String[] getCommandArgs() {
		return commandArgs;
	}
}
