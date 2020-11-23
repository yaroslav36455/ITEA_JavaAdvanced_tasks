package ua.itea;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;

public class Arguments extends ArrayList<Arg> {
	private static final long serialVersionUID = 1L;

	public Arguments(Iterable<String> keys, CommandLine cl) {
		
		for (String key : keys) {
			if (cl.hasOption(key)) {
				Arg newArg = new Arg();
				
				newArg.setKey(key);
				newArg.setValue(combine(cl.getOptionValues(key)));

				add(newArg);
			}
		}
	}
	
	private String combine(String[] values) {
		StringBuilder result = new StringBuilder();
		
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				result.append(values[i]);
				
				if (i != values.length - 1) {
					result.append(":");	
				}
			}
		}
		
		return result.toString();
	}
}
