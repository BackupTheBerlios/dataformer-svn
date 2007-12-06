package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;

public class Transformation extends DataFormerNode {

	private List<ComponentDeclaration> components;
	private String name;

	public Transformation(int line, int column, String name, List<ComponentDeclaration> components) {
		super(line, column);
		this.name = name;
		this.components = components;
	}
	
	public List<ComponentDeclaration> getComponents() {
		return components;
	}

}
