/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class VariableDeclarator extends DataFormerNode {

    public String id;

    public Expression init;

    public VariableDeclarator(int line, int column, String id, Expression init) {
        super(line, column);
        this.id = id;
        this.init = init;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
