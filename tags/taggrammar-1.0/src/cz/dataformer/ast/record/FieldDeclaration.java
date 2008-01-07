package cz.dataformer.ast.record;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.type.Type;

public class FieldDeclaration extends DataFormerNode {

	private String name;
	private Type type;

	public FieldDeclaration(int line, int column, Type type, String name) {
		super(line, column);
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
}
