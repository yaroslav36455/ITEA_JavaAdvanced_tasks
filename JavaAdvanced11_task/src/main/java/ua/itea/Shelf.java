package ua.itea;

import java.util.ArrayList;
import java.util.Collection;

public class Shelf<T extends Product> {
	private Collection<T> coll;
	
	public Shelf() {
		coll = new ArrayList<>();
	}
	
	public void add(T elem) {
		coll.add(elem);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (T elem : coll) {
			builder.append(elem.showProduct() + "\n");
		}
		
		return builder.toString();
	}
}
