/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 */
public final class ConditionalExpression extends Expression {

    public Expression condition;
    public Expression thenExpr;
    public Expression elseExpr;

    public ConditionalExpression(int line, int column, Expression condition, Expression thenExpr, Expression elseExpr) {
        super(line, column);
        this.condition = condition;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
