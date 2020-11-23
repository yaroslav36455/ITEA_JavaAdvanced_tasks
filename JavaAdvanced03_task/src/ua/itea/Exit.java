package ua.itea;

public class Exit implements Command {

	@Override
	public void execute(String[] args) {
		System.exit(0);
	}

	@Override
	public String getDescription() {
		return "e|q|exit|quit - exit the program\n";
	}

}
