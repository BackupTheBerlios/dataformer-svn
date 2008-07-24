package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

public class Port extends BodyDeclaration {

	public Modifiers modifiers;
	public String ioType;
	
	
	public Port(int line, int column, Modifiers modifiers, String name, String type) {
		super(line, column, name);
		this.modifiers = modifiers;
		this.name = name;
		this.ioType = type;
	}
	
	public boolean isInput() {
		return this.modifiers.isInput();
	}
	
	public boolean isOutput() {
		return this.modifiers.isOutput();
	}
	
	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
