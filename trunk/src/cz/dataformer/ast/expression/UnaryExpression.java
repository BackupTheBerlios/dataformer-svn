/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.UnaryOperatorEnum;


/**
 * @author mtomcany
 */
public final class UnaryExpression extends Expression {

    public Expression expr;

    public UnaryOperatorEnum op;

    public UnaryExpression(int line, int column, Expression expr, UnaryOperatorEnum op) {
        super(line, column);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
