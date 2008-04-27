/*
 * Created on 18/11/2006
 */
package cz.dataformer.ast.statement;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.body.Parameter;

/**
 * @author mtomcany
 */
public final class CatchClause extends DataFormerNode {

    public Parameter except;

    public BlockStatement catchBlock;

    public CatchClause(int line, int column, Parameter except, BlockStatement catchBlock) {
        super(line, column);
        this.except = except;
        this.catchBlock = catchBlock;
    }

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}

}
