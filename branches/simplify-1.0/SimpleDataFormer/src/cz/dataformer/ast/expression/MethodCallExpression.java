/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 */
public final class MethodCallExpression extends Expression {

    public Expression scope;
    public String name;
    public List<Expression> args;

    public MethodCallExpression(int line, int column, Expression scope, String name, List<Expression> args) {
        super(line, column);
        this.scope = scope;
        this.name = name;
        this.args = args;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
