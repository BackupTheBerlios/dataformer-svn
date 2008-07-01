package cz.dataformer.compiler.model;

import cz.dataformer.ast.ImportDeclaration;

public class ImportModel extends NamedModelNode {

	private boolean starImport;
	
	public ImportModel(ImportDeclaration ast, ModelNode owner) {
		super(ast,owner);
	}
	
	public void setStarImport(boolean isAsterisk) {
		this.starImport = isAsterisk;
	}
	
	public boolean isStarImport() {
		return starImport;
	}

}
