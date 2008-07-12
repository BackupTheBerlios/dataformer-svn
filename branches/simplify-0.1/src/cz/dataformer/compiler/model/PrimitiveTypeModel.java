package cz.dataformer.compiler.model;

import cz.dataformer.ast.type.PrimitiveType.PrimitiveTypeEnum;

public class PrimitiveTypeModel extends TypeModel {
	
	private PrimitiveTypeEnum type;

	public static final PrimitiveTypeModel INT = new PrimitiveTypeModel(PrimitiveTypeEnum.Int);
	public static final PrimitiveTypeModel BOOLEAN = new PrimitiveTypeModel(PrimitiveTypeEnum.Boolean);
	
	public PrimitiveTypeModel(PrimitiveTypeEnum type) {
		super(TypeClass.PRIMITIVE);
		this.type = type;
	}
	
	public String name() {
		return type.getName();
	}
	
	// can assign
	// convert to
	// cast

	
}
