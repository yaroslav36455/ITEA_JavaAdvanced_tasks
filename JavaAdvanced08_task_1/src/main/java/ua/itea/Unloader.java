package ua.itea;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Unloader extends Worker {
	private int unloadingSpeed;
	private Semaphore semaphore;
	
	public Unloader(int unloadingSpeed) {
		this.unloadingSpeed = unloadingSpeed;
	}
	
	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	protected void work() throws InterruptedException {
		int took = getCart().takeCargo(unloadingSpeed);
		
		TimeUnit.MILLISECONDS.sleep((int) ((float) took / unloadingSpeed * 1000));
	}

	@Override
	protected boolean next() {
		return getCart().getCargo() != 0;
	}
	
	@Override
	protected String getInfo() {
		return getCart().toString();
	}

	@Override
	protected void acquire() throws InterruptedException {
		semaphore.acquire();
	}

	@Override
	protected void release() {
		semaphore.release();
	}
}
