package cz.dataformer.compiler.model;

import cz.dataformer.ast.type.IOTypeParameter;

public class IOParamModel extends ModelNode implements NamedModelNode {

	public IOParamModel(IOTypeParameter ast, ComponentModel owner) {
		super(ast, owner);
	}
	
	public String name() {
		return ((IOTypeParameter)ast).name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof IOParamModel == false) {
			return false;
		}
		
		IOParamModel other = (IOParamModel)obj;
		return name().equals(other.name());
	}

	
	
}
