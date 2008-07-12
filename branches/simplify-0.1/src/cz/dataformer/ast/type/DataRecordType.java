/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;


/**
 * Represents data record or a reference to IO type parameter 
 * @author mtomcany
 */
public final class DataRecordType extends Type {

    public String name;
    
    public DataRecordType(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }
    
    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
