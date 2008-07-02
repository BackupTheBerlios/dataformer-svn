package cz.dataformer.compiler.model;

import java.util.HashMap;

import cz.dataformer.ast.record.RecordDeclaration;

public class RecordModel extends ModelNode implements NamedModelNode {

	private HashMap<String,FieldModel> fields = new HashMap<String,FieldModel>();
	
	public RecordModel(RecordDeclaration ast, TransformationModel owner) {
		super(ast,owner);
	}
	
	public void addField(FieldModel field) {
		fields.put(field.name(),field);
	}
	
	public int numFields() {
		return fields.size();
	}

	public FieldModel getField(String name) {
		return fields.get(name);
	}

	public String name() {
		return ((RecordDeclaration)ast).name;
	}
}
