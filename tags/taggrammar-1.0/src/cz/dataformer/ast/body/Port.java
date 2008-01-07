package cz.dataformer.ast.body;

import cz.dataformer.ast.type.GenericType;

public class Port extends BodyDeclaration {

	private Modifiers modifiers;
	private GenericType genericType;
	private String name;
	
	
	public Port(int line, int column, Modifiers modifiers, String name, GenericType type) {
		super(line, column);
		this.modifiers = modifiers;
		this.name = name;
		this.genericType = type;
	}
	
	public boolean isInput() {
		return this.modifiers.isInput();
	}
	
	public boolean isOutput() {
		return this.modifiers.isOutput();
	}

}
