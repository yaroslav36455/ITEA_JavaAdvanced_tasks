package ua.itea;

public class App {

	public static void main(String[] args) {
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
		
		loader.setCarrier(carrier);
		carrier.setLoader(loader);
		carrier.setUnloader(unloader);
		unloader.setCarrier(carrier);
		
		loader.setStopwatch(stopwatch);
		carrier.setStopwatch(stopwatch);
		unloader.setStopwatch(stopwatch);

		loader.start();
		carrier.start();
		unloader.start();
		
		loader.takeCart(cart);
	}

}
