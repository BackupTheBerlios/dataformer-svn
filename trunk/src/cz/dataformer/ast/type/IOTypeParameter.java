package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;


/**
 * Represents type as from Java Generics
 * @author mtomcany
 *
 */
public class IOTypeParameter extends Type {

	public final String name;
	
	public IOTypeParameter(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isPrimitive() {
		return false;
	}
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
