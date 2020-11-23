package ua.itea;

public abstract class Worker implements Runnable {
	private Cart cart;
	private Heap heap;
	private Stopwatch stopwatch;
	
	public void start() {
		new Thread(this).start();
	}
	
	public void setHeap(Heap heap) {
		this.heap = heap;
	}
	
	public Heap getHeap() {
		return heap;
	}

	public synchronized void takeCart(Cart cart) {
		this.cart = cart;
		this.notify();
	}
	
	public synchronized Cart giveCart() {
		Cart tmp = cart;
		cart = null;
		return tmp;
	}
	
	public Stopwatch getStopwatch() {
		return stopwatch;
	}

	public void setStopwatch(Stopwatch stopwatch) {
		this.stopwatch = stopwatch;
	}

	public Cart getCart() {
		return cart;
	}

	@Override
	public synchronized void run() {
		try {
			do {
				if (cart == null) {
					wait();
					
					if (!stopwatch.isStarted()) {
						stopwatch.start();
					}
				}
				
				System.out.println(getName() + ": work");
				System.out.println(stopwatch + "|" + cart + "|" + heap);
				while(next()) {
					work();
					System.out.println(stopwatch + "|" + cart + "|" + heap);
				}
				
				delegateWork();
				System.out.println(getName() + ": sleep");
				System.out.println("==================");
			} while(!isFinished());
			
			System.out.println(getName() + ": FINISHED");
			System.out.println("==================");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getName() {
		return getClass().getSimpleName();
	}
	protected abstract boolean next();
	protected abstract void work() throws InterruptedException;
	protected abstract void delegateWork();
	
	public boolean isFinished() {
		return heap.isEmpty();
	}
}
