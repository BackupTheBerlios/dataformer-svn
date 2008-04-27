package cz.dataformer;

public class ASTDelimitedRecordField extends RecordField {

	String delimiter;

	public ASTDelimitedRecordField(int i) {
		super(i);
	}

	public boolean isDelimited() {
		return true;
	}

	public String getDelimiter() {
		return delimiter;
	}

	@Override
	public String toString() {
		return GraphParserTreeConstants.jjtNodeName[id] + "("
				+ GraphParserConstants.tokenImage[type] + "," + name + ","
				+ delimiter + ")";
	}

	public Object jjtAccept(GraphParserVisitor visitor, Object data)
			throws VisitorException {
		return visitor.visit(this, data);
	}

}
