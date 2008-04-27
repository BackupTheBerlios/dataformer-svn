package cz.dataformer.compiler.symbol;

import java.util.Set;

import cz.dataformer.ast.Transformation;

public class TransformationSymbol extends Symbol {

	public TransformationSymbol(Transformation ast, Set<SymbolFlags> flags) {
		super(ast, ast.name, SymbolFlags.TRANSFORMATION, null, flags);
		// allocate new scope and put transformation symbol into it
		this.scope = new GlobalSymbolTable();
		try {
			this.scope.put(this);
		} catch (DuplicateDeclarationException e) {
			assert false : "Unreachable code";
		}
	}

	
}
