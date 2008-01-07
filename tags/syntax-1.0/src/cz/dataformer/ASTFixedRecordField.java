package cz.dataformer;

public class ASTFixedRecordField extends RecordField {

	int length;

	public ASTFixedRecordField(int id) {
		super(id);
	}

	public ASTFixedRecordField(GraphParser p, int id) {
		super(p, id);
	}

	public String toString() {
		return GraphParserTreeConstants.jjtNodeName[id] + "("
				+ GraphParserConstants.tokenImage[type] + "," + name + ","
				+ length + ")";
	}

	public Object jjtAccept(GraphParserVisitor visitor, Object data)
			throws VisitorException {
		return visitor.visit(this, data);
	}

}
