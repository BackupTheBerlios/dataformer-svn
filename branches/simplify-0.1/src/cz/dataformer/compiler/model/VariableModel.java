package cz.dataformer.compiler.model;

import cz.dataformer.NamedDataFormerNode;

public class VariableModel extends ModelNode 
implements NamedModelNode {

	protected TypeModel type;
	
	public VariableModel(NamedDataFormerNode ast, ModelNode owner) {
		super(ast, owner);
	}

	public String name() {
		return ((NamedDataFormerNode)ast).name;
	}
	
}
