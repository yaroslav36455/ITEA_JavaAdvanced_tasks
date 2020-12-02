package ua.itea;

import java.util.concurrent.Exchanger;

public class App {
    public static void main(String[] args ) {
    	Stopwatch stopwatch = new Stopwatch();
    	Heap heap = new Heap(100);
    	Cart cart = new Cart(6);
    	Exchanger<Cart> loaderCarrier = new Exchanger<>();
    	Exchanger<Cart> unloaderCarrier = new Exchanger<>();
    	
    	Loader loader = new Loader(3);
    	Carrier carrier = new Carrier(5);
    	Unloader unloader = new Unloader(2);
    	
    	loader.setExchanger(loaderCarrier);
    	carrier.setLoaderExchanger(loaderCarrier);
    	carrier.setUnloaderExchanger(unloaderCarrier);
    	unloader.setExchanger(unloaderCarrier);
    	
    	heap.setMass(heap.getCapacity());
    	loader.setHeap(heap);
    	carrier.setHeap(heap);
    	unloader.setHeap(heap);
    	
    	loader.setCart(cart);
    	
    	loader.setStopwatch(stopwatch);
    	carrier.setStopwatch(stopwatch);
    	unloader.setStopwatch(stopwatch);
    	
    	loader.start();
    	carrier.start();
    	unloader.start();
    }
}
