package cz.dataformer.compiler.model;

import cz.dataformer.NamedDataFormerNode;

public class NamedModelNode extends ModelNode {

	private String name;
	
	public NamedModelNode(NamedDataFormerNode ast, ModelNode owner) {
		super(ast, owner);
		this.name = ast.name;
	}
	
	public String name() {
		return this.name;
	}

}
