package cz.dataformer.compiler.model;

import java.util.HashMap;

import cz.dataformer.ast.record.RecordDeclaration;

public class RecordModel extends ModelNode implements NamedModelNode {

	private HashMap<String,VariableModel> fields = new HashMap<String,VariableModel>();
	
	public RecordModel(RecordDeclaration ast, TransformationModel owner) {
		super(ast,owner);
	}
	
	public void addField(VariableModel field) {
		fields.put(field.name(),field);
	}
	
	public int numFields() {
		return fields.size();
	}

	public VariableModel getField(String name) {
		return fields.get(name);
	}

	public String name() {
		return ((RecordDeclaration)ast).name;
	}
}
