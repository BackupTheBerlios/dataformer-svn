/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;



/**
 * Represents one of primitive Java types
 * @author mtomcany
 *
 */
public final class PrimitiveType extends Type {

	/**
	 * Enumerated type covering all basic Java primitive types
	 * 
	 * @author mtomcany
	 *
	 */
    public enum PrimitiveTypeEnum {
        Boolean, Char, Byte, Short, Int, Long, Float, Double
    }

    public final PrimitiveTypeEnum type;

    public PrimitiveType(int line, int column, PrimitiveTypeEnum type) {
        super(line, column);
        this.type = type;
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
