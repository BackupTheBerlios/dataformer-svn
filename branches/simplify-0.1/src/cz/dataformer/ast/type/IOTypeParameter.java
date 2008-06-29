package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;


/**
 * Represents IO type parameter of a component
 * 
 * @author mtomcany
 *
 */
public class IOTypeParameter extends Type {

	public String name;
	public NameExpression extension;
	
	public IOTypeParameter(int line, int column, String name, NameExpression extension) {
		super(line, column);
		this.name = name;
		this.extension = extension;
	}
	
	@Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
