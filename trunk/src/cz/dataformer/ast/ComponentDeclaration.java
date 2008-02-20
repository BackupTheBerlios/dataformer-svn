package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.Modifiers;

public class ComponentDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public String name;
	public List<BodyDeclaration> members;
	public MainBlock main;

	public ComponentDeclaration(int line, int column, Modifiers mods, String name, List<BodyDeclaration> members, MainBlock main) {
		super(line, column);
		this.modifiers = mods;
		this.name = name;
		this.members = members;
		this.main = main;
	}

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
