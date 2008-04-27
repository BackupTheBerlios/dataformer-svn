package cz.dataformer.compiler;


public class SemanticCheck {
	
	private static final SemanticCheck INSTANCE = new SemanticCheck();
	
	private XformEntry current;
	
	public static SemanticCheck getInstance() {
		return INSTANCE;
	}

	
	public void check(XformEntry entry) {
		current = entry;
		
		ASTAnnotator annotator = new ASTAnnotator();
		annotator.resolveSymbols(current);
		
//		TypeVerifier verifier = new TypeVerifier();
//		verifier.verify(current);
	}
 	
	
	
	
	/* Additional syntactic checks */
	// isArrayInitializerUsedWithArrayVariable - java?
	// arrayInitializerHasCorrectNumberOfFields - java?
	// switchHasOnlyOneDefaultEntry - java?
	
	/* Port related checks */
	// isRequiredPortConnected
	// isDuplicateConnection
	
	
	/* Property related checks */
	// isRequiredPropertySet
	
	/* Flow related checks */
	// checkCyclicDataFlow
	
	
}
