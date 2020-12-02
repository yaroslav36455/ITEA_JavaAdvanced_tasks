package ua.itea;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Carrier implements Runnable {
	private int transportationTime;
	private Exchanger<Cart> loaderExchanger;
	private Exchanger<Cart> unloaderExchanger;
	private Cart cart;
	private Heap heap;
	private Stopwatch stopwatch;

	public Carrier(int transportationTime) {
		this.transportationTime = transportationTime;
	}
	
	public void setStopwatch(Stopwatch stopwatch) {
		this.stopwatch = stopwatch;
	}
	
	public void setHeap(Heap heap) {
		this.heap = heap;
	}
	
	public void setLoaderExchanger(Exchanger<Cart> loaderExchanger) {
		this.loaderExchanger = loaderExchanger;
	}
	
	public void setUnloaderExchanger(Exchanger<Cart> unloaderExchanger) {
		this.unloaderExchanger = unloaderExchanger;
	}
	
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			stopwatch.start();
			while (true) {
				/* take cart from loader */
				cart = loaderExchanger.exchange(null);
				System.out.println(getInfo() + "carrier  start (to unloader)");
				TimeUnit.SECONDS.sleep(transportationTime);
				System.out.println(getInfo() + "carrier  end");
				
				/* give cart to unloader */
				unloaderExchanger.exchange(cart);
				cart = null;
				
				if(heap.isEmpty()) {
					break;
				}
				
				/* take cart from unloader */
				cart = unloaderExchanger.exchange(null);
				System.out.println(getInfo() + "carrier  start (to loader)");
				TimeUnit.SECONDS.sleep(transportationTime);
				System.out.println(getInfo() + "carrier  end");
				
				/* give cart to loader */
				loaderExchanger.exchange(cart);
			}
			
			System.out.println("carrier finish");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String getInfo() {
		return stopwatch + "; cargo:" + cart.getCargo() + "/" + cart.getCapacity()
			   + "; heap:" + heap.getMass() + "/" + heap.getCapacity() + " | ";
	}
}
