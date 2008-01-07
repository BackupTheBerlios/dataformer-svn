/*
 * Created on 09/03/2007
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public final class IntegerLiteralMinValueExpression extends IntegerLiteralExpression {

    public IntegerLiteralMinValueExpression(int line, int column) {
        super(line, column, "-2147483648");
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
