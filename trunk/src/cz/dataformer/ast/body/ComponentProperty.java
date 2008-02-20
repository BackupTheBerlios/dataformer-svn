package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;

public class ComponentProperty extends BodyDeclaration {

	public Modifiers modifiers;
	public Type type;
	public String name;
	

	public ComponentProperty(int line, int column, Modifiers modifiers, Type type, String name) {
		super(line, column);
		this.type = type;
		this.name = name;
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
