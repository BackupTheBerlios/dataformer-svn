package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 *
 */
public class MainBlock extends BodyDeclaration {

	public BlockStatement block;

	public MainBlock(int line, int column, BlockStatement block) {
		super(line, column, "main");
		this.block = block;
	}
	
	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}

}
