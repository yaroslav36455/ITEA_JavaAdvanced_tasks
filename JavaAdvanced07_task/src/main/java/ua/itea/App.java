package ua.itea;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
    	Mine mine = new Mine(1000);
    	Barrack barrack = new Barrack();
    	ArrayList<Miner> miners = new ArrayList<>();
    	
    	for (int i = 0; i < 5; i++) {
    		Miner m = barrack.createMiner();
    		
    		m.setMine(mine);
			miners.add(m);
		}
    	
    	int i = 0;
		try {
	    	while(mine.getGold() > 0) {
	    		TimeUnit.SECONDS.sleep(1);
	    		
	    		for (Miner miner : miners) {
					System.out.println(miner);
				}
	    		
	    		System.out.println("---------");
	    		System.out.println("Gold: " + mine.getGold());
	    		System.out.println("Seconds elapsed: " + ++i);
	    		System.out.println("=====================================");
	    		
	    		if (i % 10 == 0) {
	        		Miner m = barrack.createMiner();
	        		
	        		m.setMine(mine);
	    			miners.add(m);
	    		}
	    	}
	    	
			for (Miner miner : miners) {
				miner.getThread().join();
				System.out.println(miner);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
