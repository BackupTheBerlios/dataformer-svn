/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 * 
 */
public final class DoStatement extends Statement {

    public final Statement body;
    public final Expression condition;

    public DoStatement(int line, int column, Statement body, Expression condition) {
        super(line, column);
        this.body = body;
        this.condition = condition;
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
