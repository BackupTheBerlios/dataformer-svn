/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

/**
 * @author mtomcany
 */
public final class MethodCallExpression extends Expression {

    public final Expression scope;

    public final String name;

    public final List<Expression> args;

    public MethodCallExpression(int line, int column, Expression scope, String name, List<Expression> args) {
        super(line, column);
        this.scope = scope;
        this.name = name;
        this.args = args;
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
