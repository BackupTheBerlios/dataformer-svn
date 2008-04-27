package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.ClassOrInterfaceType;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class Port extends BodyDeclaration {

	public Modifiers modifiers;
	public ClassOrInterfaceType ioType;
	public String name;
	public VariableSymbol symbol;
	
	
	public Port(int line, int column, Modifiers modifiers, String name, ClassOrInterfaceType type) {
		super(line, column);
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
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
