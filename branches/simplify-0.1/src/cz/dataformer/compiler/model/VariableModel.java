package cz.dataformer.compiler.model;

import cz.dataformer.DataFormerNode;

public class VariableModel extends ModelNode {

	protected String name;
	protected TypeModel type;
	
	public VariableModel(DataFormerNode ast, ModelNode owner) {
		super(ast, owner);
	}
	
}
