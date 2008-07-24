/*
 * Created on 18/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ThrowStatement extends Statement {

    public Expression expr;

    public ThrowStatement(int line, int column, Expression expr) {
        super(line, column);
        this.expr = expr;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

}
