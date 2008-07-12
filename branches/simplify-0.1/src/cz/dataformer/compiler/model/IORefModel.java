package cz.dataformer.compiler.model;


public class IORefModel extends TypeModel {

	private String paramName;
	
	public IORefModel(String paramName) {
		super(TypeClass.IOREF);
		this.paramName = paramName;
	}
	
	public String name() {
		return paramName;
	}

}
