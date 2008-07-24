/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class SwitchStatement extends Statement {

    public Expression selector;
    public List<SwitchEntryStatement> entries;

    public SwitchStatement(int line, int column, Expression selector, List<SwitchEntryStatement> entries) {
        super(line, column);
        this.selector = selector;
        this.entries = entries;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}
}
