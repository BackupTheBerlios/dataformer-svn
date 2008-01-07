package cz.dataformer.ast.statement;

import cz.dataformer.ast.expression.NameExpression;

public class ConnectStatement extends Statement {

	private NameExpression sourcePort;
	private NameExpression destPort;

	public ConnectStatement(int line, int column, NameExpression sourcePort, NameExpression destPort) {
		super(line, column);
		this.sourcePort = sourcePort;
		this.destPort = destPort;
	}

}
