package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.GraphCompilerException;

public class TransformationFieldDeclaration extends NamedDataFormerNode {

	public Modifiers modifiers;
	public NameExpression type;
	public List<NameExpression> ioParams;

	public TransformationFieldDeclaration(int line, int column, Modifiers modifiers, NameExpression type, List<NameExpression> ioParams, String name) {
		super(line, column,name);
		this.modifiers = modifiers;
		this.type = type;
		this.ioParams = ioParams;
	}

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}
	
}
