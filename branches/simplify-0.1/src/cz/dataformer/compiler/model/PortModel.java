package cz.dataformer.compiler.model;

import cz.dataformer.ast.body.Port;

public class PortModel extends ModelNode implements NamedModelNode {

	
	public PortModel(Port ast, ComponentModel owner) {
		super(ast,owner);
	}
	
	public boolean isInput() {
		return ((Port)ast).isInput();
	}
	
	public String name() {
		return ((Port)ast).name;
	}
}
