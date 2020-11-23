package ua.itea;

import java.util.concurrent.TimeUnit;

public class Loader extends Worker {
	private int loadingSpeed;
	private Carrier carrier;
	
	public Loader(int loadingSpeed) {
		this.loadingSpeed = loadingSpeed;
	}
	
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	@Override
	protected boolean next() {
		Cart cart = getCart();
		Heap heap = getHeap();
		
		return cart.getCargo() != cart.getCapacity() && !heap.isEmpty();
	}

	@Override
	protected void delegateWork() {
		carrier.takeCart(giveCart());
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
}
