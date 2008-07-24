/*
 * Created on 03/11/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * Method or catch-clause parameter
 * 
 * @author mtomcany
 */
public final class Parameter extends NamedDataFormerNode {

    public Modifiers modifiers;
    public Type type;
    public boolean isVarArgs;
    
    public Parameter(int line, int column, Modifiers modifiers, Type type, boolean isVarArgs, String id) {
        super(line, column, id);
        this.modifiers = modifiers;
        this.type = type;
        this.isVarArgs = isVarArgs;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
