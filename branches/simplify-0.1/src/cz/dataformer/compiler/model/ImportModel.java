package cz.dataformer.compiler.model;

import cz.dataformer.ast.ImportDeclaration;

public class ImportModel extends ModelNode implements NamedModelNode {

	public ImportModel(ImportDeclaration ast, ModelNode owner) {
		super(ast,owner);
	}
	
	public boolean isStarImport() {
		return ((ImportDeclaration)ast).isAsterisk;
	}

	public String name() {
		return ((ImportDeclaration)ast).name;
	}

}
