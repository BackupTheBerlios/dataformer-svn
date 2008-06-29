package cz.dataformer.compiler.model;

import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.Utilities;

public class PackageModel extends ModelNode {

	private String name;

	public PackageModel(NameExpression ast, TransformationModel owner) {
		super(ast,owner);
		this.name = Utilities.nameToString(ast);
	}
	
	public String name() {
		return name;
	}
	
}
