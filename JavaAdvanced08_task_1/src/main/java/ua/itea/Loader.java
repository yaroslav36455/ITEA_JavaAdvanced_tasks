package ua.itea;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Loader extends Worker {
	private int loadingSpeed;
	private Semaphore semaphore;
	
	public Loader(int loadingSpeed) {
		this.loadingSpeed = loadingSpeed;
	}
	
	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	
	@Override
	protected boolean next() {
		Cart cart = getCart();
		
		return cart.getCargo() != cart.getCapacity() && !getHeap().isEmpty();
	}
	
	@Override
	protected void work() throws InterruptedException {
		Cart cart = getCart();
		Heap heap = getHeap();
		
		int took = Math.min(heap.getMass(), loadingSpeed);
		int overflow = cart.putCargo(took);
		int loaded = took - overflow;
		
		TimeUnit.MILLISECONDS.sleep((int) ((float) loaded / loadingSpeed * 1000));
		heap.setMass(heap.getMass() - loaded);
	}

	@Override
	protected String getInfo() {
		return getCart() + ", " + getHeap();
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
