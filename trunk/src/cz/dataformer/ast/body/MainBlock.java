package cz.dataformer.ast.body;

import cz.dataformer.ast.statement.BlockStatement;

/**
 * @author mtomcany
 *
 */
public class MainBlock extends BodyDeclaration {

	private BlockStatement block;

	public MainBlock(int line, int column, BlockStatement block) {
		super(line, column);
		this.block = block;
	}

}
