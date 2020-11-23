package ua.itea;

import java.util.concurrent.TimeUnit;

public class Carrier extends Worker {
	private int transportationTime;
	private Loader loader;
	private Unloader unloader;
	private boolean toUnloader;
	private int timeCounter;

	public Carrier(int transportationTime) {
		this.transportationTime = transportationTime;
		this.toUnloader = true;
	}
	
	public void setLoader(Loader loader) {
		this.loader = loader;
	}
	
	public void setUnloader(Unloader unloader) {
		this.unloader = unloader;
	}

	@Override
	protected boolean next() {
		return timeCounter++ < transportationTime;
	}

	@Override
	protected void work() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

	@Override
	protected void delegateWork() {
		if (toUnloader) {
			unloader.takeCart(giveCart());
		} else {
			loader.takeCart(giveCart());
		}
		toUnloader = !toUnloader;
		timeCounter = 0;
	}
}
