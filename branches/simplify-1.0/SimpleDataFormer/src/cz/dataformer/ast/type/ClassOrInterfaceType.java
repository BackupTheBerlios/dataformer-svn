/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;


/**
 * Represents classes and interfaces declaration
 * @author mtomcany
 */
public final class ClassOrInterfaceType extends Type {

    public ClassOrInterfaceType scope;
    public String name;
    
    public ClassOrInterfaceType(int line, int column, ClassOrInterfaceType scope, String name) {
        super(line, column);
        this.scope = scope;
        this.name = name;
    }
    
    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
