/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public class IntegerLiteralExpression extends StringLiteralExpression {

    public IntegerLiteralExpression(int line, int column, String value) {
        super(line, column, value);
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

    public final boolean isMinValue() {
        return value != null && //
                value.length() == 10 && //
                value.equals("2147483648");
    }
}
