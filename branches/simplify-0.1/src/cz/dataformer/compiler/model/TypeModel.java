package cz.dataformer.compiler.model;

import cz.dataformer.ast.type.Type;


public abstract class TypeModel extends ModelNode implements NamedModelNode {

	public TypeModel(Type ast, ModelNode owner) {
		super(ast, owner);
	}

	
}
