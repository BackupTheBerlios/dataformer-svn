/*
 * Created on 18/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class TryStatement extends Statement {

    public BlockStatement tryBlock;
    public List<CatchClause> catchs;
    public BlockStatement finallyBlock;

    public TryStatement(int line, int column, BlockStatement tryBlock, List<CatchClause> catchs, BlockStatement finallyBlock) {
        super(line, column);
        this.tryBlock = tryBlock;
        this.catchs = catchs;
        this.finallyBlock = finallyBlock;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

}
