/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * @author mtomcany
 */
public final class ArrayAccessExpression extends Expression {

    public Expression name;

    public Expression index;

    public ArrayAccessExpression(int line, int column, Expression name, Expression index) {
        super(line, column);
        this.name = name;
        this.index = index;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
