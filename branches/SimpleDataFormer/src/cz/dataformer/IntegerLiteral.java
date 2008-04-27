package cz.dataformer;

public class IntegerLiteral extends SimpleNode {

	// value of integer constant
	int val;

	public IntegerLiteral(int i) {
		super(i);
	}

	@Override
	public String toString() {
		return GraphParserTreeConstants.jjtNodeName[id] + "(" + val + ")";
	}

}
