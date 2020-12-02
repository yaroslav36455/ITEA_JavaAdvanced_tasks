package ua.itea;

public class Product {
	private String name;
	private float price;

	public Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String showProduct() {
		return toString();
	}

	@Override
	public String toString() {
		return "name=" + getName() + ", price=" + getPrice();
	}

	public static Product newInstance() {
		Product prod = new Product("Product " + (char) (65 + Math.random() * 25));
		
		prod.setPrice((float) (45.6 + Math.random() * 354.4));
		return prod;
	}
	
}
