/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;

/**
 * Variable declarator 
 * @author mtomcany
 */
public final class VariableDeclaratorId extends DataFormerNode {

    public String name;

    public VariableDeclaratorId(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
