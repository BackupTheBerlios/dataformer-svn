package cz.dataformer.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FileManager {

	private static final FileManager INSTANCE = new FileManager();
	private LinkedList<File> sourcePath = new LinkedList<File>();
	
	
	private FileManager() {
		// TODO: need proper initialization from
	}
	
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
		for (File sp : sourcePath) {
			File f = new File(sp,pathToFile);
			if (! f.exists()) {
				return f;
			}
			
		}

		throw new FileNotFoundException(pathToFile);
		
	}

	/**
	 * Checks if given package exists in current source path
	 * @param prefix
	 * @return
	 */
	public File getSourcePathEntry(String prefix) {
		String pathToFind = prefix.replace(".", "/");
		for (File spEntry : sourcePath) {
			File dir = new File(spEntry,pathToFind);
			if (dir.isDirectory() && dir.exists()) {
				return dir;
			}
		}
		
		return null;
	}

	public void setSourcePath(String path) {
		String delim = path.contains(";") ? ";" : ":";
		StringTokenizer tok = new StringTokenizer(path,delim);
		while (tok.hasMoreTokens()) {
			File entry = new File(tok.nextToken());
			if (entry.exists()) {
				sourcePath.add(entry);
			}
		}
	}
	
	
}