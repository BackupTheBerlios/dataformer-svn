/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 */
public final class VariableDeclarator extends DataFormerNode {

    public VariableDeclaratorId id;

    public Expression init;

    public VariableDeclarator(int line, int column, VariableDeclaratorId id, Expression init) {
        super(line, column);
        this.id = id;
        this.init = init;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
