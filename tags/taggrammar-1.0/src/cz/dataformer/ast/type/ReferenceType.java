/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;


/**
 * Represents reference type
 * - array of primitive type
 * - other class type (not used)
 * 
 * @author mtomcany
 *
 */
public final class ReferenceType extends Type {

    public final Type type;

    public final int arrayCount;

    public ReferenceType(int line, int column, Type type, int arrayCount) {
        super(line, column);
        this.type = type;
        this.arrayCount = arrayCount;
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
