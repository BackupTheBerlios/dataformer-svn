package cz.dataformer.compiler.model;

import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.compiler.Utilities;

public class ImportModel extends ModelNode {

	private String name;
	private boolean starImport;
	
	public ImportModel(ImportDeclaration ast, ModelNode owner) {
		super(ast,owner);
		this.name = Utilities.nameToString(ast.name);
	}
	
	public String name() {
		return name;
	}

	public void setStarImport(boolean isAsterisk) {
		this.starImport = isAsterisk;
	}
	
	public boolean isStarImport() {
		return starImport;
	}

}
