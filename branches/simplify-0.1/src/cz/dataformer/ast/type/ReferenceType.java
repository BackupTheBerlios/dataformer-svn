/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * Represents arrays
 * 
 * @author mtomcany
 *
 */
public final class ReferenceType extends Type {

    public Type type;
    public int arrayCount;

    public ReferenceType(int line, int column, Type type, int arrayCount) {
        super(line, column);
        this.type = type;
        this.arrayCount = arrayCount;
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
