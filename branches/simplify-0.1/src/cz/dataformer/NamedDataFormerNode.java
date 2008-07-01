package cz.dataformer;

import cz.dataformer.ast.NodeVisitor;

public abstract class NamedDataFormerNode extends DataFormerNode {

	public String name;
	
	public NamedDataFormerNode(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	
	public abstract void accept(NodeVisitor v);

}
