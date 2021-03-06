package cz.dataformer.ast.record;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class FieldDeclaration extends DataFormerNode {

	public String name;
	public Type type;
	public VariableSymbol symbol;
	
	
	public FieldDeclaration(int line, int column, Type type, String name) {
		super(line, column);
		this.name = name;
		this.type = type;
	}

	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
	
}
