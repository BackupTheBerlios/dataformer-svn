/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;


/**
 * @author mtomcany
 */
public final class ArrayAccessExpression extends Expression {

    public final Expression name;

    public final Expression index;

    public ArrayAccessExpression(int line, int column, Expression name, Expression index) {
        super(line, column);
        this.name = name;
        this.index = index;
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
