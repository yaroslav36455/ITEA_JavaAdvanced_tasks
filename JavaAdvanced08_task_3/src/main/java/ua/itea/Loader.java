package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private int loadingSpeed;
	private Exchanger<Cart> exchanger;
	private Heap heap;
	private Cart cart;
	private Stopwatch stopwatch;

	public Loader(int loadingSpeed) {
		this.loadingSpeed = loadingSpeed;
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
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			stopwatch.start();
			while (true) {
				System.out.println(getInfo() + "loader   start");
				work();
				System.out.println(getInfo() + "loader   end");

				/* give cart */
				exchanger.exchange(cart);
				cart = null;
				
				if (heap.isEmpty()) {
					break;
				}
				
				/* take cart */
				cart = exchanger.exchange(null);
			}
			
			System.out.println("loader finish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void work() throws InterruptedException {
		while(cart.getCargo() != cart.getCapacity() && !heap.isEmpty()) {
			int loaded = load();
			TimeUnit.MILLISECONDS.sleep(1000 * loaded / loadingSpeed);
		}
	}
	
	private int load() {
		int loaded = Math.min(heap.getMass(), loadingSpeed);
		heap.setMass(heap.getMass() - loaded);
		cart.putCargo(loaded);
		return loaded;
	}
	
	private String getInfo() {
		return stopwatch + "; cargo:" + cart.getCargo() + "/" + cart.getCapacity()
			   + "; heap:" + heap.getMass() + "/" + heap.getCapacity() + " | ";
	}
}
