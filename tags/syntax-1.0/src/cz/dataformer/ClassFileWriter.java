package cz.dataformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassFileWriter {

	private File rootDirectory;
	private BufferedWriter writer;
	
	public ClassFileWriter(String rootDirectory) {
		this.rootDirectory = new File(rootDirectory);
	}
	
	public void openFile(String relativeFile) throws IOException {
		openFile(relativeFile,false);
	}
	
	public void openFile(File relativeFile) throws IOException {
		openFile(relativeFile.getPath());
	}
	
	public void appendFile(String relativeFile) throws IOException {
		openFile(relativeFile,true);
	}
	
	public void closeFile() throws IOException {
		writer.flush();
		writer.close();
	}
	
	public void writeToken(String token) throws IOException {
		writer.write(token + "\n");
	}
	
	private void openFile(String relativeFile, boolean append) throws IOException {
		File openFile = new File(rootDirectory,relativeFile);
		openFile.getParentFile().mkdirs();
		writer = new BufferedWriter(new FileWriter(openFile,append));
	}
	
}
