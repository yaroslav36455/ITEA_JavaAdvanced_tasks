package ua.itea;

public class Stopwatch {
	private boolean isStarted;
	private long time;
	
	public void start() {
		restart();
	}

	public void restart() {
		time = System.nanoTime();
		isStarted = true;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	
	@Override
	public String toString() {
		long elapsed = (System.nanoTime() - time) / 1000_000;
		return String.format("%3d.%03d",
				elapsed / 1000, elapsed % 1000);
	}
}
