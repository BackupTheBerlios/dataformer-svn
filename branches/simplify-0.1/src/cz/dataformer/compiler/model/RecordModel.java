package cz.dataformer.compiler.model;

import java.util.HashMap;

import cz.dataformer.ast.record.RecordDeclaration;

public class RecordModel extends ModelNode {

	private String name;
	private HashMap<String,FieldModel> fields = new HashMap<String,FieldModel>();
	
	public RecordModel(RecordDeclaration ast, TransformationModel owner) {
		super(ast,owner);
		this.name = ast.name;
	}
	
	public void addField(FieldModel field) {
		fields.put(field.name(),field);
	}
	
	public boolean hasFields() {
		return fields.size() > 0;
	}
}
