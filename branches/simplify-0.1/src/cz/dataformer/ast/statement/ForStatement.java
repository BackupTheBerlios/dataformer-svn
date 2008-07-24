/*
 * Created on 07/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ForStatement extends Statement {

    public List<Expression> init;
    public List<Expression> update;
    public Expression iterable;
    public Statement body;

    public ForStatement(int line, int column, List<Expression> init, Expression iterable, List<Expression> update, Statement body) {
        super(line, column);
        this.iterable = iterable;
        this.init = init;
        this.update = update;
        this.body = body;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

}
