package cz.dataformer.ast;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.compiler.GraphCompilerException;

public class PackageDeclaration extends NamedDataFormerNode {


	public PackageDeclaration(int line, int column, String name) {
		super(line, column,name);
	}

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

	
}
