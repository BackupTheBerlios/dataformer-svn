package cz.dataformer.compiler.model;

import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.type.PrimitiveType;

public class FieldModel extends VariableModel {

	public FieldModel(FieldDeclaration ast, RecordModel owner) {
		super(ast, owner);
		this.name = ast.name;
		this.type = new PrimitiveTypeModel((PrimitiveType)ast.type,this);
	}
	
	public String name() {
		return name;
	}

	
}