package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

public class ConnectStatement extends Statement {

	public Expression sourcePort;
	public Expression destPort;

	public ConnectStatement(int line, int column, Expression sourcePort, Expression destPort) {
		super(line, column);
		this.sourcePort = sourcePort;
		this.destPort = destPort;
	}
	
	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
