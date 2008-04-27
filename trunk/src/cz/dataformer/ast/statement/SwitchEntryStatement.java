/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;

/**
 * @author mtomcany
 * 
 * Represents <code>case</code> or <code>default</code> statement inside of
 * switch body. If {@link #label} is <code>null</code>, it is a <code>default</code>
 * statement.
 */
public final class SwitchEntryStatement extends Statement {

    public Expression label;
    public List<Statement> stmts;

    public SwitchEntryStatement(int line, int column, Expression label, List<Statement> stmts) {
        super(line, column);
        this.label = label;
        this.stmts = stmts;
    }

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}

}
