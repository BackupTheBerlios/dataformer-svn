package cz.dataformer.compiler.symbol;

public enum SymbolFlags {

	// Symbol class
	CONSTANT("constant"),
	VARIABLE("variable"),
	FIELD("field"),
	METHOD("method"),
	PROPERTY("property"),
	PORT("port"),
	TYPE("type"),
	
	
	// Additional type flags
	PRIMITIVE("primitive"),	// primitive type
	GENERIC("generic"), 	// generic type referencing and IO parameter
	RECORD("record"),		// data record type
	COMPONENT("component"),	// component
	TRANSFORMATION("transformation"),	// transformation
	
	
	// Java Modifiers
	ABSTRACT("abstract"), 
	FINAL("final"), 
	INPUT("input"), 
	NATIVE("native"), 
	OPTIONAL("optional"), 
	OUTPUT("output"), 
	PRIVATE("private"), 
	PROTECTED("protected"), 
	PUBLIC("public"), 
	REQUIRED("required"), 
	STATIC("static"), 
	STRICTFP("strictfp"), 
	SYNCHRONIZED("synchronized"), 
	TRANSIENT("transient"), 
	VOLATILE("volatile");
	
	private final String desc;
	
	SymbolFlags(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
	
	public String description() {
		return desc;
	}
	
}
