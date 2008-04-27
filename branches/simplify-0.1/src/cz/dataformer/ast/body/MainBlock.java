package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.statement.BlockStatement;

/**
 * @author mtomcany
 *
 */
public class MainBlock extends BodyDeclaration {

	public BlockStatement block;

	public MainBlock(int line, int column, BlockStatement block) {
		super(line, column);
		this.block = block;
	}
	
	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}

}
