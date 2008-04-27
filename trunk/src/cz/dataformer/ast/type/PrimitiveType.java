/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;



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
        Boolean("boolean"),
        Char("char"),
        Byte("byte"),
        Short("short"),
        Int("int"),
        Long("long"),
        Float("float"),
        Double("double");
        
        private final String name;
        
        private PrimitiveTypeEnum(String name) {
        	this.name = name;
        }
        
        public String getName() {
			return name;
		}
    }

    public PrimitiveTypeEnum type;

    public PrimitiveType(int line, int column, PrimitiveTypeEnum type) {
        super(line, column);
        this.type = type;
    }
    
    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
