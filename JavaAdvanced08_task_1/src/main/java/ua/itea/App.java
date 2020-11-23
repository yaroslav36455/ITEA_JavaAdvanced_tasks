package ua.itea;

import java.util.concurrent.Semaphore;

public class App {

	public static void main(String[] args) {
		Semaphore loadSemaphore = new Semaphore(0, true);
		Semaphore unloadSemaphore = new Semaphore(0, true);
		
		Heap heap = new Heap(100);
		Loader loader = new Loader(3);
		Carrier carrier = new Carrier(5);
		Unloader unloader = new Unloader(2);
		Cart cart = new Cart(6);
		Stopwatch stopwatch = new Stopwatch();
		
		heap.setMass(heap.getCapacity());
		
		loader.setHeap(heap);
		carrier.setHeap(heap);
		unloader.setHeap(heap);
		
		loader.setSemaphore(loadSemaphore);
		carrier.setLoadSemaphore(loadSemaphore);
		carrier.setUnloadSemaphore(unloadSemaphore);
		unloader.setSemaphore(unloadSemaphore);
		
		loader.setCart(cart);
		unloader.setCart(cart);
		carrier.setCart(cart);
		
		loader.setStopwatch(stopwatch);
		carrier.setStopwatch(stopwatch);
		unloader.setStopwatch(stopwatch);
		
		loader.start();
		carrier.start();
		unloader.start();
		
		loadSemaphore.release();
	}

}
