/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class WhileStatement extends Statement {

    public final Expression condition;

    public final Statement body;

    public WhileStatement(int line, int column, Expression condition, Statement body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
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
