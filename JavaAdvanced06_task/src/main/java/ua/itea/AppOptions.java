package ua.itea;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class AppOptions {
	private ArrayList<String> keys;
	private Options options = new Options();
	
	public AppOptions() {
		options.addOption(OptionBuilder.withArgName("files")
								   	   .hasOptionalArgs()
								   	   .withDescription("copy files")
								   	   .withLongOpt("send")
								   	   .create("s"));

		options.addOption(OptionBuilder.hasArg()
								   	   .withDescription("find something")
								   	   .create("find"));

		options.addOption(OptionBuilder.withArgName("project name")
								   	   .hasArg()
								   	   .withDescription("create some project")
								   	   .withLongOpt("project")
								   	   .create("p"));

		options.addOption(OptionBuilder.withDescription("make something")
								   	   .withLongOpt("make")
								   	   .create());

		options.addOption(OptionBuilder.withDescription("print help")
								   	   .withLongOpt("help")
								   	   .create("h"));
		
		keys = new ArrayList<>();
		for (Object obj : options.getOptions()) {
			Option opt = (Option) obj;
			keys.add(opt.getOpt() != null ? opt.getOpt() : opt.getLongOpt());
		}
	}
	
	public Iterable<String> getKeys() {
		return keys;
	}
	
	public Options getOptions() {
		return options;
	}
}
