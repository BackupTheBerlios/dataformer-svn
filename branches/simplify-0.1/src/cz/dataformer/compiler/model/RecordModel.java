package cz.dataformer.compiler.model;

import java.util.HashMap;

import cz.dataformer.ast.record.RecordDeclaration;

public class RecordModel extends TopLevelModel implements NamedModelNode {

	private HashMap<String,VariableModel> fields = new HashMap<String,VariableModel>();
	
	public RecordModel(RecordDeclaration ast) {
		super(ast,null);
		records = new HashMap<String,ImportModel>();
	}
	
	@Override
	public void addComponentImport(ImportModel imp) {
		throw new IllegalStateException("Record declaration can never import a component");
	}
	
	
	@Override
	public void addTransformationImport(ImportModel imp) {
		throw new IllegalStateException("Record declaration can never import a transformation");
	}
	
	@Override
	public ComponentModel getComponent(String name) {
		throw new IllegalStateException("Record declaration never contains any component declaration");
	}
	
	@Override
	public ComponentModel getComponent(String prefix, String simpleName) {
		throw new IllegalStateException("Record declaration never contains any component declaration");
	}
	
	@Override
	public TransformationModel getTransformation(String prefix, String name) {
			throw new IllegalStateException("Record declaration never contains any transformation declaration");
	}
	
	@Override
	public TransformationModel getTransformation(String simpleName) {
			throw new IllegalStateException("Record declaration never contains any transformation declaration");
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
