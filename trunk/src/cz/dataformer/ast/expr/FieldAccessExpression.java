/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public final class FieldAccessExpression extends Expression {

    public final Expression scope;

    public final String field;

    public FieldAccessExpression(int line, int column, Expression scope, String field) {
        super(line, column);
        this.scope = scope;
        this.field = field;
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
