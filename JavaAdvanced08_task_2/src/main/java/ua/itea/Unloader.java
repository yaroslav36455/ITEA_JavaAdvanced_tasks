package ua.itea;

import java.util.concurrent.TimeUnit;

public class Unloader extends Worker {
	private int unloadingSpeed;
	private Carrier carrier;
	
	public Unloader(int unloadingSpeed) {
		this.unloadingSpeed = unloadingSpeed;
	}
	
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
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
	protected void delegateWork() {
		if (!carrier.isFinished()) {
			carrier.takeCart(giveCart());	
		}
	}
}
