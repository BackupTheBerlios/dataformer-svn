package cz.dataformer.ast.record;

import java.util.List;

import cz.dataformer.DataFormerNode;

public class RecordDeclaration extends DataFormerNode {

	private String name;
	private List<FieldDeclaration> fields;

	public RecordDeclaration(int line, int column, String name, List<FieldDeclaration> fields) {
		super(line, column);
		this.name = name;
		this.fields = fields;
	}

	public String getName() {
		return name;
	}
	
	public List<FieldDeclaration> getFields() {
		return fields;
	}
}
