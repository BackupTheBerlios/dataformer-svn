/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.BinaryOperatorEnum;
import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 */
public final class BinaryExpression extends Expression {

    public Expression left;
    public Expression right;
    public BinaryOperatorEnum op;

    public BinaryExpression(int line, int column, Expression left, Expression right, BinaryOperatorEnum op) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
