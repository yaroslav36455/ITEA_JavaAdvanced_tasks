package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private int unloadingSpeed;
	private Exchanger<Cart> exchanger;
	private Cart cart;
	private Heap heap;
	private Stopwatch stopwatch;
	
	public Unloader(int unloadingSpeed) {
		this.unloadingSpeed = unloadingSpeed;
	}
	
	public void setStopwatch(Stopwatch stopwatch) {
		this.stopwatch = stopwatch;
	}
	
	public void setHeap(Heap heap) {
		this.heap = heap;
	}
	
	public void setExchanger(Exchanger<Cart> exchanger) {
		this.exchanger = exchanger;
	}
	
	public  void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			stopwatch.start();
			while(true) {
				
				/* take cart */
				cart = exchanger.exchange(null);
				System.out.println(getInfo() + "unloader start");
				work();
				System.out.println(getInfo() + "unloader end");
				
				if(heap.isEmpty()) {
					break;
				}
				
				/* give cart */
				exchanger.exchange(cart);
				cart = null;
			}
			
			System.out.println("unloader finish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void work() throws InterruptedException {
		while(cart.getCargo() != 0) {
			int unloaded = unload();
			TimeUnit.MILLISECONDS.sleep(1000 * unloaded / unloadingSpeed);	
		}
	}

	private int unload() {
		return cart.takeCargo(unloadingSpeed);
	}
	
	private String getInfo() {
		return stopwatch + "; cargo:" + cart.getCargo() + "/" + cart.getCapacity()
			   + "; heap:" + heap.getMass() + "/" + heap.getCapacity() + " | ";
	}
}
