package ua.itea;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopier implements Command {
	private boolean replaceIfExists = false;
	private boolean generateNewNameIfExists = false;
	private Path source;
	private Path target;

	public FileCopier() {
		refresh();
	}

	private void refresh() {
		replaceIfExists = false;
		generateNewNameIfExists = false;
		source = null;
		target = null;
	}

	public void execute(String[] args) throws CommandArgsException, FileAlreadyExistsException,
									          NoSuchFileException, IOException {
		refresh();
		parseArguments(args);
		
		checkExistens(source);
		checkExistens(target.getParent());
		
		boolean fileExists = target.toFile().exists(); 
		if (fileExists && replaceIfExists) {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} else {
			if (fileExists && generateNewNameIfExists) {
				generateTargetNameAutomatically();
			}
			
			Files.copy(source, target);	
		}
	}
	
	private void checkExistens(Path path) throws NoSuchFileException {
		if (!path.toFile().exists()) {
			throw new NoSuchFileException("No such file:"
										  + path.toAbsolutePath().toString());
		}
	}

	private void parseArguments(String[] args) throws CommandArgsException {
		for (String arg : args) {
			parseArgument(arg);
		}
		
		if (target == null) {
			target = source;
		}
	}
	
	private void parseArgument(String arg) throws CommandArgsException {
		if (arg.startsWith("-")) {
			parseKeys(arg.substring(1));
		} else if (source == null) {
			source = Paths.get(arg).toAbsolutePath();
		} else if (target == null) {
			target = Paths.get(arg).toAbsolutePath();
		} else {
			throw new CommandArgsException("Unexpected argument " + arg);
		}
	}
	
	private void parseKeys(String keys) throws CommandArgsException {
		for (int i = 0; i < keys.length(); i++) {
			switch (keys.charAt(i)) {
			case 'r':
				if (generateNewNameIfExists == true) {
					throw new CommandArgsException("Inconsistent arguments -r and -g");
				}
				replaceIfExists = true;
				break;
				
			case 'g':
				if (replaceIfExists == true) {
					throw new CommandArgsException("Inconsistent arguments -r and -g");
				}
				generateNewNameIfExists = true;
				break;
				
			default:
				throw new CommandArgsException("Illegal key " + keys.charAt(i));
			}
		}
	}
	
	private void generateTargetNameAutomatically() {
		File file = target.toFile();
		String fileName = file.getName();
		String shortFileName;
		String extFileName;
		int dotPos = fileName.indexOf(".", fileName.charAt(0) == '.' ? 1 : 0);
		
		/* example: .file<_copy_<i>>.proj.mvc.rst
		 * .file - short file name
		 * .proj.mvc.rst - extensions
		 * */
		
		if (dotPos == -1 || dotPos == 0) {
			shortFileName = fileName;
			extFileName = "";	
		}
		else {
			shortFileName = fileName.substring(0, dotPos);
			extFileName = fileName.substring(dotPos);
		}
		
		int i = 1;
		while(file.exists()) {
			file = new File(shortFileName + "_copy_" + i++ + extFileName);
		}
		
		target = Paths.get(file.getAbsolutePath());
	}

	@Override
	public String getDescription() {
		return "cp|copy [-r|-g] <source file> <destination file> - copy file\n"
			  +"     -r - (default false) replace automatically if destination file exists\n"
			  +"     -g - (default false) generate new filename automatically if destination file exists\n";
	}

}
