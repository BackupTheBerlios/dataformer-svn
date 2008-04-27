package cz.dataformer.compiler;

import java.io.File;
import java.io.FileNotFoundException;

public class FileManager {

	private static final FileManager INSTANCE = new FileManager();
	
	public static final FileManager getInstance() {
		return INSTANCE;
	}
	
	public File resolve(String fqn) 
	throws FileNotFoundException, IllegalArgumentException {

		String pathToFile = null;
		
		if  (fqn.endsWith(DefaultsAndConstants.GRAPH_FILE_SUFFIX)) {
			/*
			 * Resolving regular file
			 * Check for invalid names like This.Is.Ill.def which would 
			 * confuse package resolution
			 */
			int dotCount = 0;
			for (char c : fqn.toCharArray()) {
				if (c == '.') {
					dotCount++;
					if (dotCount > 1) {
						// already ill formed
						break;
					}
				}
			}
			
			if (dotCount > 1) {
				throw new IllegalArgumentException("Illegal transformation file name: " 
						+ fqn);
			}
			
			pathToFile = fqn;
			
		} else {
			/*
			 * Resolving import statement request in the form
			 * import cz.cuni.example.Transform
			 * 
			 * Translate dots to slashes and add file suffix
			 */
			StringBuilder buf = new StringBuilder(fqn.replace('.','/'));
			buf.append(".").append(DefaultsAndConstants.GRAPH_FILE_SUFFIX);

			pathToFile = buf.toString();
		}
		
		// check existence
		File f = new File(pathToFile);
		
		if (! f.exists()) {
			throw new FileNotFoundException(pathToFile);
		}
		
		return f;
		
	}
	
	
}