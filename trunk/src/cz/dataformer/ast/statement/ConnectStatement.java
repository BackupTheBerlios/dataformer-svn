package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;

public class ConnectStatement extends Statement {

	public NameExpression sourcePort;
	public NameExpression destPort;

	public ConnectStatement(int line, int column, NameExpression sourcePort, NameExpression destPort) {
		super(line, column);
		this.sourcePort = sourcePort;
		this.destPort = destPort;
	}
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
