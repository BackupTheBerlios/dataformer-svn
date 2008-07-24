/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * Represents various types of possibly qualified names 
 * 
 * @author mtomcany
 */
public class NameExpression extends Expression {

	public String name;
	public String prefix;
	
    public NameExpression(int line, int column, String prefix,String name) {
        super(line, column);
        this.name = name;
        this.prefix = prefix;
    }
    
    public NameExpression(int line, int column, String name) {
    	this(line,column,null,name);
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

    public boolean isQualified() {
    	return prefix != null;
    }
    
}
