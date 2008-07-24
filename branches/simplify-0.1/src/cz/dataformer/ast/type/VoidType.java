/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * Void type as returned from methods
 * @author mtomcany
 *
 */
public final class VoidType extends Type {

    public VoidType(int line, int column) {
        super(line, column);
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
