package ua.itea;

public class Heap {
	private int mass;
	private int capacity;

	public Heap() {
		/* empty */
	}
	
	public Heap(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public int getMass() {
		return mass;
	}
	
	public void setMass(int mass) {
		this.mass = mass;
	}
	
	public boolean isFull() {
		return mass == capacity;
	}
	
	public boolean isEmpty() {
		return mass == 0;
	}
	
	@Override
	public String toString() {
		return "Heap:" + getMass() + "/" + getCapacity();
	}
}
