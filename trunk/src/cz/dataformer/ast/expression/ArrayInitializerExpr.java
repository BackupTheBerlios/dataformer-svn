/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 */
public final class ArrayInitializerExpr extends Expression {

    public List<Expression> values;

    public ArrayInitializerExpr(int line, int column, List<Expression> values) {
        super(line, column);
        this.values = values;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
