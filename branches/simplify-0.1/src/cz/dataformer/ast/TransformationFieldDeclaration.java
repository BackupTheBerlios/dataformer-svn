package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class TransformationFieldDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public NameExpression type;
	public List<NameExpression> ioParams;
	public String name;
	public VariableSymbol symbol;

	public TransformationFieldDeclaration(int line, int column, Modifiers modifiers, NameExpression type, List<NameExpression> ioParams, String name) {
		super(line, column);
		this.modifiers = modifiers;
		this.type = type;
		this.ioParams = ioParams;
		this.name = name;
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
	
}
