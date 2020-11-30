package ua.itea;

import ua.itea.cat.SomeCat;

public class CatFarshFactory {
	public Farsh createFarsh(Class<? extends SomeCat> someCatClass) {
		CatClassState state = new CatClassState(someCatClass);
		System.out.println(state);
		
		return state.permit() ? new Farsh() : null;
	}
}
