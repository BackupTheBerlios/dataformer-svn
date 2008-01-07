/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.UnaryOperatorEnum;


/**
 * @author mtomcany
 */
public final class UnaryExpression extends Expression {

    public final Expression expr;

    public final UnaryOperatorEnum op;

    public UnaryExpression(int line, int column, Expression expr, UnaryOperatorEnum op) {
        super(line, column);
        this.expr = expr;
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
