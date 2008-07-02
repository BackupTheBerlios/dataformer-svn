package cz.dataformer.compiler.model;

import cz.dataformer.ast.PackageDeclaration;

public class PackageModel extends ModelNode implements NamedModelNode {


	public PackageModel(PackageDeclaration ast, TransformationModel owner) {
		super(ast,owner);
	}

	public String name() {
		return ((PackageDeclaration)ast).name;
	}
	
}
