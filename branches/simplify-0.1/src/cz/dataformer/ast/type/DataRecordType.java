/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * Represents data record or a reference to IO type parameter 
 * @author mtomcany
 */
public final class DataRecordType extends Type {

    public NameExpression name;
    
    public DataRecordType(int line, int column, NameExpression name) {
        super(line, column);
        this.name = name;
        StringBuffer buf;
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
