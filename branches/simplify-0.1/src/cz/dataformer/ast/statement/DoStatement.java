/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 * 
 */
public final class DoStatement extends Statement {

    public Statement body;
    public Expression condition;

    public DoStatement(int line, int column, Statement body, Expression condition) {
        super(line, column);
        this.body = body;
        this.condition = condition;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
