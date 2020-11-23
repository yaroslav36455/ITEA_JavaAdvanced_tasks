package ua.itea;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		CommandReader commandReader = new CommandReader(System.in);
		Path path = ((PathChanger) Commands.PATH_CHANGER.getCommand()).getPath();
		
		while(true) {
			System.out.print(path.toAbsolutePath() + "$ ");
			try {
				commandReader.read();
				
				switch(commandReader.getCommandName()) {
				case "cd":
				case "chdr":
					Commands.PATH_CHANGER.execute(commandReader.getCommandArgs());
					path = ((PathChanger) Commands.PATH_CHANGER.getCommand()).getPath();
					break;
					
				case "ls":
				case "list":
				case "dir":
					Commands.FILE_LIST_PRINTER.execute(commandReader.getCommandArgs());
					break;
					
				case "op":
				case "open":
				case "read":
				case "cat":
				case "print":
					Commands.FILE_PRINTER.execute(commandReader.getCommandArgs());
					break;
					
				case "cp":
				case "copy":
					Commands.FILE_COPIER.execute(commandReader.getCommandArgs());
					break;
				
				case "h":
				case "help":
					Commands.HELP_PRINTER.execute(null);
					break;
				
				case "q":
				case "e":
				case "quit":
				case "exit":
					Commands.EXIT.execute(null);
					break;
					
				default:
					throw new CommandNameMismatchException("Command mismatch: "
														   + commandReader.getCommandName());
				}
				
			} catch (CommandNameMismatchException | CommandArgsException e) {
				System.out.println(e.getMessage());
				try {
					Commands.HELP_PRINTER.execute(null);
				} catch (CommandArgsException | IOException e1) {
					e1.printStackTrace();
				}
			} catch(IOException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
}
