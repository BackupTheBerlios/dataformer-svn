package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.type.ComponentType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.compiler.symbol.ComponentSymbol;

public class ComponentDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public String name;
	public List<IOTypeParameter> ioParams;
	public List<BodyDeclaration> members;
	public MainBlock main;
	public ComponentSymbol symbol;
	public ComponentType extend;

	public ComponentDeclaration(int line, int column, Modifiers mods, String name, List<IOTypeParameter> ioParams, List<BodyDeclaration> members, MainBlock main, ComponentType extend) {
		super(line, column);
		this.modifiers = mods;
		this.name = name;
		this.ioParams = ioParams;
		this.members = members;
		this.main = main;
		this.extend = extend;
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
