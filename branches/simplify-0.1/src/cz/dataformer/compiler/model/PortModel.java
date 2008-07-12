package cz.dataformer.compiler.model;

import cz.dataformer.ast.body.Port;

public class PortModel extends ModelNode implements NamedModelNode {

	private IORefModel type;
	
	public PortModel(Port ast, ComponentModel owner, IORefModel type) {
		super(ast,owner);
		this.type = type;
	}
	
	public boolean isInput() {
		return ((Port)ast).isInput();
	}
	
	public String name() {
		return ((Port)ast).name;
	}
}
