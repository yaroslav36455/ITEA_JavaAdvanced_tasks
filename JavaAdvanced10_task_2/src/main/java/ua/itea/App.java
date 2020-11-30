package ua.itea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import ua.itea.cat.AglyCat;
import ua.itea.cat.BlackCat;
import ua.itea.cat.Cat;
import ua.itea.cat.FatCat;
import ua.itea.cat.SomeCat;
import ua.itea.cat.ThinCat;

public class App {
    public static void main(String[] args) {
    	Collection<Class<? extends SomeCat>> coll = Arrays.asList(ThinCat.class,
    															  AglyCat.class,
    															  FatCat.class,
    															  Cat.class,
    															  BlackCat.class);
    	Collection<Farsh> farsh = new ArrayList<>();
    	CatFarshFactory farshFactory = new CatFarshFactory();
    	
    	for (Class<? extends SomeCat> catClass : coll) {
    		Farsh f = farshFactory.createFarsh(catClass);
    		
    		if (f != null) {
    			farsh.add(f);
    		}
		}
    }
}
