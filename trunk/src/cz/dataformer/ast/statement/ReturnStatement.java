/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class ReturnStatement extends Statement {

    public Expression expr;

    public ReturnStatement(int line, int column, Expression expr) {
        super(line, column);
        this.expr = expr;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
