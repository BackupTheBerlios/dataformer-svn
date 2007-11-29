/*
 * Created on 05/10/2006
 */
package cz.dataformer.type;


/**
 * Represents classes and interfaces declaration
 * @author mtomcany
 */
public final class ClassOrInterfaceType extends Type {

    public final ClassOrInterfaceType scope;

    public final String name;

    public ClassOrInterfaceType(int line, int column, ClassOrInterfaceType scope, String name) {
        super(line, column);
        this.scope = scope;
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
