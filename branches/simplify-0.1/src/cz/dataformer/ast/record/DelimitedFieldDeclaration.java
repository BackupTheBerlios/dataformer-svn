package cz.dataformer.ast.record;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;

public class DelimitedFieldDeclaration extends FieldDeclaration {

	public String delimiter;
	
	public DelimitedFieldDeclaration(int line, int column, Type type, String name, String delimiter) {
		super(line, column, type, name);
		this.delimiter = delimiter;
	}
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
