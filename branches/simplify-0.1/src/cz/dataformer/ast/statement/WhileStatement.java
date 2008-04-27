/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class WhileStatement extends Statement {

    public Expression condition;
    public Statement body;

    public WhileStatement(int line, int column, Expression condition, Statement body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
