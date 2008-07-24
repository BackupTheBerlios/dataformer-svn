package cz.dataformer.ast.record;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.GraphCompilerException;

public class DelimitedFieldDeclaration extends FieldDeclaration {

	public String delimiter;
	
	public DelimitedFieldDeclaration(int line, int column, PrimitiveType type, String name, String delimiter) {
		super(line, column, type, name);
		this.delimiter = delimiter;
	}
	
	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
