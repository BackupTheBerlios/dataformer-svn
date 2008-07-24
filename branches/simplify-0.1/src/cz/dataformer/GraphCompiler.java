package cz.dataformer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.Transformation;
import cz.dataformer.compiler.FileManager;
import cz.dataformer.compiler.ParserWrapper;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.SemanticCheck;
import cz.dataformer.compiler.XformEntry;
import cz.dataformer.compiler.model.TransformationModel;
import cz.dataformer.compiler.model.TopLevelModel.TopLevel;

public class GraphCompiler {

	private final ParserWrapper parser = ParserWrapper.getInstance();
	private final SemanticCheck semantic = SemanticCheck.getInstance();
	private final ProblemReporter pr = ProblemReporter.getInstance();
	
	
	/**
	 * Top level working queue holding transformations to compile.
	 * New transformations are inserted for import statements.
	 */
	private List<XformEntry> todoQueue = new LinkedList<XformEntry>();
	
	private void parseAndRegister(String[] args) {
		
		for (int i=0; i<args.length; i++) {
			if ("-sp".equals(args[i])) {
				FileManager fm = FileManager.getInstance();
				fm.setSourcePath(args[++i]);
			} else {
				XformEntry entry = parser.parse(new File(args[i++]),TopLevel.TRANSFORMATION);
				todoQueue.add(entry);
			}
			
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
