package cz.dataformer.compiler.model;

import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.ast.type.PrimitiveType.PrimitiveTypeEnum;

public class PrimitiveTypeModel extends TypeModel {

	private PrimitiveTypeEnum type; 
	
	public PrimitiveTypeModel(PrimitiveType ast, ModelNode owner) {
		super(ast, owner);
		type = ast.type;
	}
	
	public String name() {
		return type.getName();
	}

}
