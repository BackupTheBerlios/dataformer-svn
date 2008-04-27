package cz.dataformer;

import java.util.LinkedList;
import java.util.List;

import cz.dataformer.compiler.ParserWrapper;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.SemanticCheck;
import cz.dataformer.compiler.XformEntry;

public class GraphCompiler {

	private final ParserWrapper parser = ParserWrapper.getInstance();
	private final SemanticCheck semantic = SemanticCheck.getInstance();
	private final ProblemReporter pr = ProblemReporter.getInstance();
	
	
	/**
	 * Top level working queue holding transformations to compile.
	 * New transformations are inserted for import statements.
	 */
	private List<XformEntry> todoQueue = new LinkedList<XformEntry>();
	
	private void parseAndRegister(String[] files) {
		for (String f : files) {
			XformEntry entry = parser.parse(f);
			todoQueue.add(entry);
		}
		
	}
	
	private void extractTypes() {
		for (XformEntry xe : todoQueue) {
			semantic.check(xe);
		}
	}
	
	
	public void compile(String[] args) {
		parseAndRegister(args);
		if (pr.errorCount() == 0) {
			extractTypes();
		}
		
		pr.writeErrors();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphCompiler compiler = new GraphCompiler();
		compiler.compile(args);
	}

}
