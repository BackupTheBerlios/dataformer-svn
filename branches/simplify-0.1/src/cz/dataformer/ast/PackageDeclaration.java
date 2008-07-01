package cz.dataformer.ast;

import cz.dataformer.NamedDataFormerNode;

public class PackageDeclaration extends NamedDataFormerNode {


	public PackageDeclaration(int line, int column, String name) {
		super(line, column,name);
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}

	
}
