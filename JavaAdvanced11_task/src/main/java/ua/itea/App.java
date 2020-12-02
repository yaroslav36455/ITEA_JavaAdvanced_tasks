package ua.itea;

public class App {
    public static void main(String[] args) {
    	Shelf<Product> shelfA = new Shelf<>();
    	Shelf<WeightProduct> shelfB = new Shelf<>();
    	Shelf<WeightColorProduct> shelfC = new Shelf<>();
    	
    	shelfA.add(Product.newInstance());
    	shelfA.add(Product.newInstance());
    	shelfA.add(WeightProduct.newInstance());
    	shelfA.add(WeightProduct.newInstance());
    	shelfA.add(WeightColorProduct.newInstance());
    	shelfA.add(WeightColorProduct.newInstance());
    	System.out.println("Shelf<Product> shelfA:");
    	System.out.println(shelfA);
    	System.out.println();
    	
    	shelfB.add(WeightProduct.newInstance());
    	shelfB.add(WeightProduct.newInstance());
    	shelfB.add(WeightProduct.newInstance());
    	shelfB.add(WeightColorProduct.newInstance());
    	shelfB.add(WeightColorProduct.newInstance());
    	System.out.println("Shelf<WeightProduct> shelfB:");
    	System.out.println(shelfB);
    	System.out.println();
    	
    	shelfC.add(WeightColorProduct.newInstance());
    	shelfC.add(WeightColorProduct.newInstance());
    	shelfC.add(WeightColorProduct.newInstance());
    	shelfC.add(WeightColorProduct.newInstance());
    	shelfC.add(WeightColorProduct.newInstance());
    	System.out.println("Shelf<WeightColorProduct> shelfC:");
    	System.out.println(shelfC);
    	System.out.println();
    }
}
