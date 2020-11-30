package ua.itea;

import ua.itea.cat.SomeCat;

public class CatClassState {
	private Class<? extends SomeCat> catClass;
	private boolean blohable;
	private ColorEnum color;
	private int age;
	private boolean permit;
	
	public CatClassState(Class<? extends SomeCat> catClass) {
		this.catClass = catClass;
		blohable = catClass.isAnnotationPresent(Blohable.class);
		color = catClass.getAnnotation(Color.class).color();
		age = catClass.getAnnotation(CatYears.class).old();
		
		permit = !blohable
				 && (color != ColorEnum.BLACK || Math.random() < 0.5)
				 && age <= 3;
	}

	public boolean isBlohable() {
		return blohable;
	}

	public ColorEnum getColor() {
		return color;
	}

	public int getAge() {
		return age;
	}
	
	public boolean permit() {
		return permit;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(100);
		
		builder.append(catClass + ":\n");
		builder.append("  Blohable: " + blohable + "\n");
		builder.append("  Color: " + color + "\n");
		builder.append("  Age: " + age + "\n");
		builder.append("  Decision: " + (permit() ? "approve" : "reject") + "\n");

		return builder.toString();
	}
}
