package cz.dataformer;

public class RecordField extends SimpleNode {

	int type;
	String name;

	RecordField(int id) {
		super(id);
	}

	RecordField(GraphParser p, int id) {
		super(p,id);
	}
	
	@Override
	public String toString() {
		return GraphParserTreeConstants.jjtNodeName[id] + "("
				+ GraphParserConstants.tokenImage[type] + "," + name + ")";
	}
	
	public boolean isDelimited() {
		return false;
	}
	
	public String getFieldTypeAsString() {
		return type == GraphParserConstants.INTEGER ? "int" : "boolean";
	}

	
}
