package ua.itea;

import java.util.concurrent.TimeUnit;

public class Miner implements Runnable {
	private String name;
	private Mine mine;
	private int goldPerSecond;
	private int gold;
	private Thread thread;

	public Miner(String name) {
		this.name = name;
		goldPerSecond = 3;
	}
	
	public void setMine(Mine mine) {
		this.mine = mine;
		thread = new Thread(this);
		thread.start();
	}

	public String getName() {
		return name;
	}
	
	public int getGold() {
		return gold;
	}
	
	public Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		
		try {
			int took;
			do {
				TimeUnit.SECONDS.sleep(1);
				took = mine.takeGold(goldPerSecond);
				gold += took;
			} while (took == goldPerSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return name + ":" + gold;
	}
}
