package cz.dataformer.ast.body;

import cz.dataformer.ast.type.Type;

public class ComponentProperty extends BodyDeclaration {

	private Type type;
	private String name;

	public ComponentProperty(int line, int column, Type type, String name) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

}
