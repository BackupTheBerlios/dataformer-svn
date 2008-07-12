package cz.dataformer.compiler.model;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.Utilities;

public class VariableModel extends ModelNode implements NamedModelNode {

	private TypeModel type;
	
	public VariableModel(Parameter ast, MethodModel owner, TypeModel type) {
		super(ast, owner);
		this.type = type;
	}
	
	public VariableModel(FieldDeclaration ast, RecordModel owner, PrimitiveTypeModel type) {
		super(ast,owner);
		this.type = type;
	}
	
	public String name() {
		return ((NamedDataFormerNode)ast).name;
	}
	
	public TypeModel type() {
		return type;
	}
	
}
