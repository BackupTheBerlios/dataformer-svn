package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * Represents IO type parameter of a component
 * 
 * @author mtomcany
 *
 */
public class IOTypeParameter extends Type {

	public String name;
	public String extension;
	
	public IOTypeParameter(int line, int column, String name, String extension) {
		super(line, column);
		this.name = name;
		this.extension = extension;
	}
	
	@Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
