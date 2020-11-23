package ua.itea;

import java.io.IOException;

public interface Command {
	public void execute(String[] args) throws CommandArgsException, IOException;
	public String getDescription();
}
