package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.type.Type;

public class ComponentVariableDeclaration extends DataFormerNode {

	private List<Type> ioTypes;
	private String name;
	private List<BodyDeclaration> body;

	public ComponentVariableDeclaration(int line, int column, List<Type> ioTypes, String name, List<BodyDeclaration> body) {
		super(line, column);
		this.ioTypes = ioTypes;
		this.name = name;
		this.body = body;
	}

}
