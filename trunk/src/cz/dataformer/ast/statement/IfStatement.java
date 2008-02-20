/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class IfStatement extends Statement {

    public Expression condition;
    public Statement thenStmt;
    public Statement elseStmt;

    public IfStatement(int line, int column, Expression condition, Statement thenStmt, Statement elseStmt) {
        super(line, column);
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
