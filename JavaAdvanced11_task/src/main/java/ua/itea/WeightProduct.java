package ua.itea;

public class WeightProduct extends Product {
	private float weight;

	public WeightProduct(String name) {
		super(name);
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return super.toString() + ", weight=" + weight;
	}
	
	public static WeightProduct newInstance() {
		WeightProduct prod = new WeightProduct("WeightProduct "
											   + (char) (65 + Math.random() * 25));
		
		prod.setPrice((float) (35.6 + Math.random() * 537.76));
		prod.setWeight((float) (3.4 + Math.random() * 53.6));
		return prod;
	}
}
