/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class ReturnStatement extends Statement {

    public final Expression expr;

    public ReturnStatement(int line, int column, Expression expr) {
        super(line, column);
        this.expr = expr;
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
