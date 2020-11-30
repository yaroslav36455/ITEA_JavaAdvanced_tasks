package ua.itea.model;

public class Execution {
	private Id id;
	private Phase phase;
	private Goals goals;
	
	@Override
	public String toString() {
		return "Execution [id=" + id + ", phase=" + phase + ", goals=" + goals + "]";
	}
}
