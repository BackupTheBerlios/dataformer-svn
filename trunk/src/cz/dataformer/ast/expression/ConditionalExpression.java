/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

/**
 * @author mtomcany
 */
public final class ConditionalExpression extends Expression {

    public final Expression condition;

    public final Expression thenExpr;

    public final Expression elseExpr;

    public ConditionalExpression(int line, int column, Expression condition, Expression thenExpr, Expression elseExpr) {
        super(line, column);
        this.condition = condition;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
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
