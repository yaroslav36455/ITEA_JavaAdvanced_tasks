package ua.itea;

public class Cart {
	private int capacityMass;
	private int cargoMass;
	
	public Cart(int capacityMass) {
		this.capacityMass = capacityMass;
	}

	public int getCapacity() {
		return capacityMass;
	}
	
	public int getCargo() {
		return cargoMass;
	}
	
	public int takeCargo(int requestMass) {
		int oldCargoMass = cargoMass;
		
		cargoMass -= Math.min(cargoMass, requestMass);
		return oldCargoMass - cargoMass;
	}
	
	public int putCargo(int putMass) {
		int emptySpace = capacityMass - cargoMass;
		
		cargoMass += Math.min(emptySpace, putMass);
		return Math.max(putMass - emptySpace, 0);
	}
	
	@Override
	public String toString() {
		return "Cargo:" + getCargo() + "/" + getCapacity();
	}
}
