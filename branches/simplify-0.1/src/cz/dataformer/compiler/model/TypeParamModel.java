package cz.dataformer.compiler.model;

import cz.dataformer.ast.type.IOTypeParameter;

public class TypeParamModel extends TypeModel {

	public TypeParamModel(IOTypeParameter ast, ModelNode owner) {
		super(ast, owner);
	}
	
	public String name() {
		return ((IOTypeParameter)ast).name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof TypeParamModel == false) {
			return false;
		}
		
		TypeParamModel other = (TypeParamModel)obj;
		return name().equals(other.name());
	}

	
	
}
