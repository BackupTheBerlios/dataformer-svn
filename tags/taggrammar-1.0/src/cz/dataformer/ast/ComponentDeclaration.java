package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.body.MainBlock;

public class ComponentDeclaration extends DataFormerNode {

	private String name;
	private List<BodyDeclaration> members;
	private MainBlock main;

	public ComponentDeclaration(int line, int column, String name, List<BodyDeclaration> members, MainBlock main) {
		super(line, column);
		this.name = name;
		this.members = members;
		this.main = main;
	}

	public String getName() {
		return name;
	}
	
	public List<BodyDeclaration> getMembers() {
		return members;
	}
	
}
