/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.ast.expression.VariableDeclarationExpression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * Foreach loop node
 * 
 * @author mtomcany
 */
public final class ForeachStatement extends Statement {

    public VariableDeclarationExpression var;
    public Expression iterable;
    public Statement body;

    public ForeachStatement(int line, int column, VariableDeclarationExpression var, Expression iterable, Statement body) {
        super(line, column);
        this.var = var;
        this.iterable = iterable;
        this.body = body;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

    
}
