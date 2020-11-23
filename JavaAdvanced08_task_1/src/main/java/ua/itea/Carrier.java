package ua.itea;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Carrier extends Worker {
	private int transportationTime;
	private int timeCounter;
	private boolean nearLoader;
	private Semaphore loadSemaphore;
	private Semaphore unloadSemaphore;

	public Carrier(int transportationTime) {
		this.transportationTime = transportationTime;
		this.nearLoader = true;
	}
	
	public void setLoadSemaphore(Semaphore loadSemaphore) {
		this.loadSemaphore = loadSemaphore;
	}
	
	public void setUnloadSemaphore(Semaphore unloadSemaphore) {
		this.unloadSemaphore = unloadSemaphore;
	}

	@Override
	protected boolean next() {
		return timeCounter++ < transportationTime;
	}

	@Override
	protected void work() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

	@Override
	protected String getInfo() {
		return getCart().toString();
	}

	@Override
	protected void acquire() throws InterruptedException {
		if (nearLoader) {
			loadSemaphore.acquire();
		} else {
			unloadSemaphore.acquire();
		}
		
		nearLoader = !nearLoader;
	}

	@Override
	protected void release() {
		if (nearLoader) {
			loadSemaphore.release();
		} else {
			unloadSemaphore.release();
		}
		
		timeCounter = 0;
	}
}
