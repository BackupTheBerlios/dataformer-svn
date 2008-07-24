/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ArrayInitializerExpression extends Expression {

    public List<Expression> values;

    public ArrayInitializerExpression(int line, int column, List<Expression> values) {
        super(line, column);
        this.values = values;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
