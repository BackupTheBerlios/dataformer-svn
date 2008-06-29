package cz.dataformer.compiler.model;

import cz.dataformer.ast.body.Port;

public class PortModel extends ModelNode {

	private String name;
	private String ioParam;
	private boolean isInput = false;
	
	public PortModel(Port ast, ModelNode owner) {
		super(ast,owner);
		this.name = ast.name;
		this.ioParam = ast.ioType;
		this.isInput = ast.isInput();
	}
	
	public boolean isInput() {
		return isInput;
	}
}
