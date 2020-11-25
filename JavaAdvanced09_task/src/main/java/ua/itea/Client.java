package ua.itea;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Client implements Callable<Boolean> {
	private String name;
	private long timeBegin;

	public Client(String name, long timeBegin) {
		this.name = name;
		this.timeBegin = timeBegin;
	}

	@Override
	public Boolean call() throws Exception {
		TimeUnit.MILLISECONDS.sleep((long) (5 * 1000 + Math.random() * 15 * 1000));
		System.out.println(name + " connected, elapsed: "
						   + (double) (System.nanoTime() - timeBegin) / 1000_000_000L);
		return null;
	}

}
