/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

/**
 * @author mtomcany
 */
public final class ArrayInitializerExpr extends Expression {

    public final List<Expression> values;

    public ArrayInitializerExpr(int line, int column, List<Expression> values) {
        super(line, column);
        this.values = values;
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
