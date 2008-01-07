/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public final class BooleanLiteralExpression extends LiteralExpression {

    public final Boolean value;

    public BooleanLiteralExpression(int line, int column, Boolean value) {
        super(line, column);
        this.value = value;
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
