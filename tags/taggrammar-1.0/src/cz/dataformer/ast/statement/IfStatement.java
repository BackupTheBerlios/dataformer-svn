/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class IfStatement extends Statement {

    public final Expression condition;
    public final Statement thenStmt;
    public final Statement elseStmt;

    public IfStatement(int line, int column, Expression condition, Statement thenStmt, Statement elseStmt) {
        super(line, column);
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
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
