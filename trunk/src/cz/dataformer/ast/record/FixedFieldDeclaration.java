package cz.dataformer.ast.record;

import cz.dataformer.ast.type.Type;

public class FixedFieldDeclaration extends FieldDeclaration {

	private int length;

	public FixedFieldDeclaration(int line, int column, Type type, String name, int length) {
		super(line, column, type, name);
		assert length > 0 : "Field length must be greater than 0" ;
		this.length = length;
	}

}
