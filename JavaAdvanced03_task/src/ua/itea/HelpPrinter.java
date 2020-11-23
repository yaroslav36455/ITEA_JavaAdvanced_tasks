package ua.itea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class HelpPrinter implements Command {
	private OutputStreamWriter osw;
	private String[] descriptions;
	
	public HelpPrinter(OutputStream os, String... descriptions) {
		this.osw = new OutputStreamWriter(os);
		this.descriptions = descriptions;
	}

	@Override
	public void execute(String[] args) throws IOException, CommandArgsException {
		if (args == null || args.length == 0) {
			for (String description : descriptions) {
				osw.write(description);
			}
			osw.write(getDescription());
		} else {
			throw new CommandArgsException("Unexpected number of arguments: " + args.length);
		}
		
		osw.flush();
	}

	@Override
	public String getDescription() {
		return "help - print this help\n";
	}

}
