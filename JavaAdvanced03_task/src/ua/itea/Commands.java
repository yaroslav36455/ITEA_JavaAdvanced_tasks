package ua.itea;

import java.io.IOException;

public enum Commands {
	PATH_CHANGER(new PathChanger()),
	FILE_COPIER(new FileCopier()),
	FILE_PRINTER(new FilePrinter(System.out, (PathChanger) PATH_CHANGER.getCommand())),
	FILE_LIST_PRINTER(new FileListPrinter(System.out, (PathChanger) PATH_CHANGER.getCommand())),
	EXIT(new Exit()),
	HELP_PRINTER(new HelpPrinter(System.out, Commands.PATH_CHANGER.getDescription(),
											 Commands.FILE_LIST_PRINTER.getDescription(),
											 Commands.FILE_PRINTER.getDescription(),
											 Commands.FILE_COPIER.getDescription(),
											 Commands.EXIT.getDescription()));
	
	private Command command;
	
	private Commands(Command command) {
		this.command = command;
	}
	
	public void execute(String[] args) throws CommandArgsException, IOException {
		command.execute(args);
	}
	
	public String getDescription() {
		return command.getDescription();
	}
	
	public Command getCommand() {
		return command;
	}
}
