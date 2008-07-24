package cz.dataformer.compiler.model;

import cz.dataformer.DataFormerNode;

public class ModelNode {

	protected ModelNode owner;
	protected DataFormerNode ast;
	
	public ModelNode(DataFormerNode ast, ModelNode owner) {
		this.ast = ast;
		this.owner = owner;
	}
	
	public ModelNode getOwner() {
		return owner;
	}
	
	 public <T extends DataFormerNode> T getAst() {
		return (T)ast;
	}
}
