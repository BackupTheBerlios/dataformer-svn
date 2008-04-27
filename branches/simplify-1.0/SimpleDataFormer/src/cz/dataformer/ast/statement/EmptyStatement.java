/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;


/**
 * @author mtomcany
 */
public final class EmptyStatement extends Statement {

    public EmptyStatement(int line, int column) {
        super(line, column);
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
