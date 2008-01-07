/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public class LongLiteralExpression extends StringLiteralExpression {

    public LongLiteralExpression(int line, int column, String value) {
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
                value.length() == 20 && //
                value.startsWith("9223372036854775808") && //
                (value.charAt(19) == 'L' || value.charAt(19) == 'l');
    }
}
