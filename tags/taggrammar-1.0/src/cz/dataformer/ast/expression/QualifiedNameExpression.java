/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;


/**
 * Represents qualified name
 * 
 * @author mtomcany
 */
public final class QualifiedNameExpression extends NameExpression {

    public final NameExpression qualifier;

    public QualifiedNameExpression(int line, int column, NameExpression scope, String name) {
        super(line, column, name);
        this.qualifier = scope;
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
