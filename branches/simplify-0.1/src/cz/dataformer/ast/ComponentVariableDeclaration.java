package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class ComponentVariableDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public NameExpression type;
	public List<Type> ioTypes;
	public String name;
	public List<BodyDeclaration> body;
	public VariableSymbol symbol;

	public ComponentVariableDeclaration(int line, int column, Modifiers modifiers, NameExpression type, List<Type> ioTypes, String name, List<BodyDeclaration> body) {
		super(line, column);
		this.modifiers = modifiers;
		this.type = type;
		this.ioTypes = ioTypes;
		this.name = name;
		this.body = body;
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
	
}
