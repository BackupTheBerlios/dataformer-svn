package cz.dataformer.ast.body;

import cz.dataformer.ast.type.Type;

public class ComponentProperty extends BodyDeclaration {

	private Modifiers modifiers;
	private Type type;
	private String name;
	

	public ComponentProperty(int line, int column, Modifiers modifiers, Type type, String name) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

}
