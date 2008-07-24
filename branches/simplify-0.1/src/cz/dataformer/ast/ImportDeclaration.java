/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast;

import cz.dataformer.DataFormerNode;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ImportDeclaration extends DataFormerNode {

    public final boolean isStar;
    public String prefix;
    public String simpleName;
    
    
    /**
     * Single-type import 
     * 
     * @param line
     * @param column
     * @param prefix
     * @param isAsterisk
     */
    public ImportDeclaration(int line, int column, String prefix) {
    	super(line, column);
    	this.prefix = prefix;
    	this.simpleName = null;
    	this.isStar = true;
    }
    
    /**
     * Star import
     * 
     * @param line
     * @param column
     * @param prefix
     * @param simpleName
     */
    public ImportDeclaration(int line, int column, String prefix, String simpleName) {
    	super(line,column);
    	this.prefix = prefix;
    	this.simpleName = simpleName;
    	this.isStar = false;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
    
    public String getFqn() {
    	return isStar ? prefix : prefix + "." + simpleName;
    }
    
}
