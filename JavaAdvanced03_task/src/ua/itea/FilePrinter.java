package ua.itea;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePrinter implements Command {
	private PathChanger pathChanger;
	private Path[] paths;
	private OutputStream os;
	
	public FilePrinter(OutputStream os, PathChanger pathChanger) {
		this.pathChanger = pathChanger;
		this.os = os;
	}
	
	public void execute(String[] args) throws IOException, CommandArgsException {
		parseArguments(args);
		
		for (Path path : paths) {
			checkExistens(path);
			checkIsFile(path);
		}
		
		for(Path path : paths) {
			Files.copy(path, os);
		}
	}

	private void checkIsFile(Path path) throws CommandArgsException {
		if (!path.toFile().isFile()) {
			throw new CommandArgsException("Directory instead of a file:"
										  + path.toAbsolutePath().toString());
		}
	}

	private void checkExistens(Path path) throws NoSuchFileException {
		if (!path.toFile().exists()) {
			throw new NoSuchFileException("No such file:"
										  + path.toAbsolutePath().toString());
		}
	}

	private void parseArguments(String[] args) {
		paths = new Path[args.length];
		for (int i = 0; i < paths.length; i++) {
			paths[i] = pathChanger.getPath().resolve(Paths.get(args[i]));
		}
	}

	@Override
	public String getDescription() {
		return "op|open|read|cat|print <files...> - print files content\n";
	}
	
}
