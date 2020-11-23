package ua.itea;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileListPrinter implements Command {
	private OutputStreamWriter osw;
	private PathChanger pathChanger;
	private boolean isHidden;
	private boolean isFile;
	private boolean isDir;
	
	public FileListPrinter(OutputStream os, PathChanger pathChanger) {
		this.osw = new OutputStreamWriter(os);
		this.pathChanger = pathChanger;
		refreshKeys();
	}
	
	public void setCurrentDirectory(PathChanger pathChanger) {
		this.pathChanger = pathChanger;
	}

	public void execute(String[] args) throws CommandArgsException, IOException {
		StringBuffer buffer = new StringBuffer();
		
		setKeys(args);
		for (File file : pathChanger.getPath().toFile().listFiles()) {
			if (isHidden || !file.isHidden()) {
				
				if (file.isFile() && isFile || file.isDirectory() && isDir) {
					buffer.append(file.getName());
					buffer.append(' ');
				}
			}
		}
		buffer.append('\n');
		
		osw.write(buffer.toString());
		osw.flush();
	}
	
	private void setKeys(String[] args) throws CommandArgsException {
		refreshKeys();
		parseArgs(args);
	}
	
	private void refreshKeys() {
		isHidden = false;
		isFile = true;
		isDir = true;
	}

	private void parseArgs(String[] args) throws CommandArgsException {
		boolean fileOrDirKeyTouched = false;
		
		for (String arg : args) {
			for (int i = 0; i < arg.length(); i++) {
				if (arg.charAt(i) != '-') {
					switch (arg.charAt(i)) {
					case 'h': /* show hidden */
						isHidden = true;
						break;
						
					case 'f': /* show files only */
						if (!fileOrDirKeyTouched) {
							isDir = false;
							fileOrDirKeyTouched = true;
						}
						isFile = true;
						break;
						
					case 'd': /* show directories only */
						if (!fileOrDirKeyTouched) {
							isFile = false;
							fileOrDirKeyTouched = true;
						}
						isDir = true;
						break;

					default:
						throw new CommandArgsException("Unexpeted argument '" + arg.charAt(i) + "'");
					}
				}
			}
		}
	}

	@Override
	public String getDescription() {
		return "ls|list|dir [-h[-f|-d]] - print directory content\n"
			  +"     -h - (default false) print hidden files/directories\n"
			  +"     -f - (default true) show files and hide directories(if -d is not specified)\n"
			  +"     -d - (default true) show directosies and hide files(if -f is not specified)\n";
	}

}
