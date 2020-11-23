package ua.itea;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathChanger implements Command{
	private Path path;
	private String root = "/";
	private String delim = "/";
	
	public PathChanger() {
		path = Paths.get("").toAbsolutePath();
	}
	
	public PathChanger(String root, Path initDirectory) {
		path = initDirectory;
		this.root = root;
	}
	
	public void execute(String[] args) throws CommandArgsException, NoSuchFileException {
		
		if (args.length == 0) {
			setPathRoot();
		} else if (args.length == 1) {
			setPath(args[0]);
		} else {
			throw new CommandArgsException("Unexpected number of arguments: " + args.length);
		}
	}
	
	public Path getPath() {
		return path;
	}
	
	private void setPathRoot() {
		path = path.getRoot().toAbsolutePath();
	}
	
	private void setPath(String dir) throws CommandArgsException, NoSuchFileException {
		Path savedPath = path;
		
		String[] steps = null;
		int leftBound = 0;
		int rightBound = dir.length();
		
		if (dir.startsWith(root)) {
			setPathRoot();
			leftBound += root.length();
		}
		
		if(dir.length() > 1 && dir.endsWith(delim)) {
			rightBound -= delim.length();
		}
		
		steps = dir.substring(leftBound, rightBound).split(delim);
		
		for (String step : steps) {
			if(step.equals("..")) {
				if (!path.getRoot().equals(path)) {
					path = path.getParent();	
				}
			} else if (step.equals(".")) {
				continue;
			} else {
				path = path.resolve(step);
			}
		}
		
		if (!path.toFile().exists()) {
			Path tmp = path;
			path = savedPath;
			throw new NoSuchFileException("No such path: " + tmp.toAbsolutePath().toString());
		}
	}

	@Override
	public String getDescription() {
		return "cd|chdr <directory> - change current dirictory\n";
	}
}
