/*
 * Created on 05/10/2006
 */
package cz.dataformer.expr;


/**
 * Represents various types of names:
 * - package names in import
 * - qualified names in qualified expressions
 * 
 * @author mtomcany
 */
public class NameExpression extends Expression {

    public final String name;

    public NameExpression(int line, int column, String name) {
        super(line, column);
        this.name = name;
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
