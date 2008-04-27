/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 */
public final class ContinueStatement extends Statement {

    public final String id;

    public ContinueStatement(int line, int column, String id) {
        super(line, column);
        this.id = id;
    }

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}

}
