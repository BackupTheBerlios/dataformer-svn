package cz.dataformer.compiler.symbol;

import cz.dataformer.compiler.DataFormerCompilationException;

public class DuplicateDeclarationException extends DataFormerCompilationException {

	public DuplicateDeclarationException(Symbol s) {
		super("Duplicate " + s.getSymbolClass().description() + " " + s.getName(),s);
	}

	public DuplicateDeclarationException(Symbol newSym, Symbol dup) {
		super("'" + newSym.getName() + "'"
			+ " already declared as " 
			+ "'" + dup.getSymbolClass().description() +"'",newSym); 
	}
}
