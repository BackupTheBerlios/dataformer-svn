package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

public class StreamOperationExpression extends Expression {

	public Expression left;
	public Expression right;
	public boolean isWrite;

	public StreamOperationExpression(int line, int column, Expression left, Expression right, boolean isWrite) {
		super(line, column);
		this.left = left;
		this.right = right;
		this.isWrite = isWrite;
	}
	
	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
	
}
