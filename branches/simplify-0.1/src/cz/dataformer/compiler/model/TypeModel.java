package cz.dataformer.compiler.model;



public abstract class TypeModel implements NamedModelNode {

	public enum TypeClass {
		PRIMITIVE, 	// type is a java.lang primitive type - int or boolean
		RECORD,		// type is a DataFormer record
		IOREF;		// type references component's IO parameters
	}
	
	private final TypeClass typeClass;
	
	public TypeModel(TypeClass typeClass) {
		this.typeClass = typeClass;
	}
	
	public TypeClass typeClass() {
		return this.typeClass;
	}

	
}
