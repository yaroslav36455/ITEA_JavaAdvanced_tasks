package ua.itea;

public abstract class Worker implements Runnable {
	private Cart cart;
	private Heap heap;
	private Stopwatch stopwatch;
	
	public void start() {
		new Thread(this).start();
	}
	
	public Heap getHeap() {
		return heap;
	}

	public void setHeap(Heap heap) {
		this.heap = heap;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public Stopwatch getStopwatch() {
		return stopwatch;
	}

	public void setStopwatch(Stopwatch stopwatch) {
		this.stopwatch = stopwatch;
	}
	
	@Override
	public void run() {
		try {
			do {
				acquire();
				System.out.println(getName() + ": work");
				
				if(!stopwatch.isStarted()) {
					stopwatch.start();
				}
				
				System.out.println(stopwatch + "|" + cart + "|" + heap);
				while(next()) {
					work();
					System.out.println(stopwatch + "|" + cart + "|" + heap);
				}
				
				System.out.println(getName() + ": sleep");
				System.out.println("==================");
				release();
			} while(!isFinished());
			
			System.out.println(getName() + ": FINISHED");
			System.out.println("==================");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void acquire() throws InterruptedException;
	protected abstract void release();

	protected abstract String getInfo();

	protected String getName() {
		return getClass().getSimpleName();
	}
	
	protected abstract boolean next();
	protected abstract void work() throws InterruptedException;
	
	public final boolean isFinished() {
		return heap.isEmpty();
	}
}
