package ua.itea;

public class Mine {
	private int gold;

	public Mine(int gold) {
		this.gold = gold;
	}

	public int getGold() {
		return gold;
	}

	public synchronized int takeGold(int request) {
		int result = Math.min(gold, request);
		
		gold -= result;
		return result;
	}
}
