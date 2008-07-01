package cz.dataformer.compiler.model;

import cz.dataformer.ast.PackageDeclaration;

public class PackageModel extends NamedModelNode {


	public PackageModel(PackageDeclaration ast, TransformationModel owner) {
		super(ast,owner);
	}
	
}
