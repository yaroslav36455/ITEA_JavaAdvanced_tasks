package ua.itea;

import java.awt.Color;

public class WeightColorProduct extends WeightProduct {
	private Color color;

	public WeightColorProduct(String name) {
		super(name);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", color=[r=" + color.getRed()
								  + ", g=" + color.getGreen()
								  + ", b=" + color.getBlue() + "]";
	}

	public static WeightColorProduct newInstance() {
		WeightColorProduct prod = new WeightColorProduct("WeightColorProduct "
											   + (char) (65 + Math.random() * 25));
		
		prod.setPrice((float) (3.6 + Math.random() * 548.67));
		prod.setWeight((float) (34.4 + Math.random() * 65.1));
		prod.setColor(new Color((int) (Math.random() * Integer.MAX_VALUE)));
		return prod;
	}
}
