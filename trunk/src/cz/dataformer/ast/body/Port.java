package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.GenericType;

public class Port extends BodyDeclaration {

	public Modifiers modifiers;
	public GenericType genericType;
	public String name;
	
	
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
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
