package cz.dataformer.ast.expression;

public class StreamOperationExpression extends Expression {

	private Expression left;
	private Expression right;
	private boolean isWrite;

	public StreamOperationExpression(int line, int column, Expression left, Expression right, boolean isWrite) {
		super(line, column);
		this.left = left;
		this.right = right;
		this.isWrite = isWrite;
	}
	
}
