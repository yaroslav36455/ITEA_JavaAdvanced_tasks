package ua.itea;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class App {
    public static void main(String[] args) {    	
    	final int count = 10; 
    	ExecutorService es = Executors.newFixedThreadPool(count);
    	ArrayList<Future<Boolean>> fa = new ArrayList<>(count);
    	final long beginNano = System.nanoTime();
    	final long waitingNano = 15 * 1000_000_000L;
    	
    	for (int i = 0; i < count; i++) {
    		fa.add(es.submit(new Client(String.valueOf((char) (65 + i)), beginNano)));
		}
    	
    	try {
			for (Future<Boolean> future : fa) {
				long restNano = System.nanoTime() - beginNano;
				
				future.get(waitingNano - restNano, TimeUnit.NANOSECONDS);
			}
			
			System.out.println("Server started");
		} catch (TimeoutException e) {
			System.out.println("Connection lost. This game is safe to leave");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    	
    	es.shutdownNow();
    	
    	double delta = (double) (System.nanoTime() - beginNano) / 1000_000_000L;
    	System.out.println("delta time:" + delta);
    }
}
