package cz.dataformer.compiler.model;

import cz.dataformer.ast.body.MethodDeclaration;

public class MethodModel extends ModelNode implements NamedModelNode {

	
	public MethodModel(MethodDeclaration ast, ComponentModel owner) {
		super(ast, owner);
	}

	public String name() {
		return ((MethodDeclaration)ast).name;
	}

}
