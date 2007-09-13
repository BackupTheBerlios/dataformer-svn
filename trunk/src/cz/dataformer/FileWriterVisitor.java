package cz.dataformer;

import java.io.File;
import java.io.IOException;

public abstract class FileWriterVisitor {

	private ClassFileWriter writer;
	
	protected FileWriterVisitor() {
		writer = new ClassFileWriter(GeneratorProperties.ROOT_DIRECTORY);
	}
	
	protected void writeToken(String token) throws IOException {
		writer.writeToken(token);
	}

	protected void openFile(File relativeFile) throws IOException {
		writer.openFile(relativeFile);
	}

	protected void openFile(String relativeFile) throws IOException {
		writer.openFile(relativeFile);
	}

	public void appendFile(String relativeFile) throws IOException {
		writer.appendFile(relativeFile);
	}

	public void closeFile() throws VisitorException {
		try {
			writer.closeFile();
		} catch (IOException e) {
			throw new VisitorException("Unable to close file",e);
		}
	}
	
	
	
	
}
