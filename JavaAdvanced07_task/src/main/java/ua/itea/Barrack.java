package ua.itea;

public class Barrack {
	private int id;
	
	public Miner createMiner() {
		return new Miner("Miner_" + ++id);
	}
}
