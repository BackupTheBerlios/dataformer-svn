package cz.dataformer.ast.record;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.GraphCompilerException;

public class FixedFieldDeclaration extends FieldDeclaration {

	public int length;

	public FixedFieldDeclaration(int line, int column, PrimitiveType type, String name, int length) {
		super(line, column, type, name);
		assert length > 0 : "Field length must be greater than 0" ;
		this.length = length;
	}

	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
	
}
