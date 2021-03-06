package cz.dataformer.ast.record;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.GraphCompilerException;

public class FieldDeclaration extends NamedDataFormerNode {

	public PrimitiveType type;
	
	
	public FieldDeclaration(int line, int column, PrimitiveType type, String name) {
		super(line, column,name);
		this.type = type;
	}

	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
	
}
