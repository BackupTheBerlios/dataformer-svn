/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.BinaryOperatorEnum;

/**
 * @author mtomcany
 */
public final class BinaryExpression extends Expression {


    public final Expression left;

    public final Expression right;

    public final BinaryOperatorEnum op;

    public BinaryExpression(int line, int column, Expression left, Expression right, BinaryOperatorEnum op) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.op = op;
    }

//    @Override
//    public <A> void accept(VoidVisitor<A> v, A arg) {
//        v.visit(this, arg);
//    }
//
//    @Override
//    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
//        return v.visit(this, arg);
//    }

}
