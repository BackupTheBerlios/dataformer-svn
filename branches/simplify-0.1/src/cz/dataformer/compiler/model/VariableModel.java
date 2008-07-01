package cz.dataformer.compiler.model;

import cz.dataformer.NamedDataFormerNode;

public class VariableModel extends NamedModelNode {

	protected TypeModel type;
	
	public VariableModel(NamedDataFormerNode ast, ModelNode owner, String name) {
		super(ast, owner);
	}
	
}
