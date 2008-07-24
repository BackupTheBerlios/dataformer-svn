/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ConditionalExpression extends Expression {

    public Expression condition;
    public Expression thenExpr;
    public Expression elseExpr;

    public ConditionalExpression(int line, int column, Expression condition, Expression thenExpr, Expression elseExpr) {
        super(line, column);
        this.condition = condition;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
