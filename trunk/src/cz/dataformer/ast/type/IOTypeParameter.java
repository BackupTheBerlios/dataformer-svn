package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;


/**
 * Represents IO type parameter of a component
 * 
 * @author mtomcany
 *
 */
public class IOTypeParameter extends Type {

	public String name;
	
	public IOTypeParameter(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
