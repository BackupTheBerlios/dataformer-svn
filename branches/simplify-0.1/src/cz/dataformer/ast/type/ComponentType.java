/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * Type for components
 * 
 * @author mtomcany
 */
public final class ComponentType extends Type {

    public NameExpression name;
    public List<IOTypeParameter> actParams;
    
    public ComponentType(int line, int column, NameExpression name, List<IOTypeParameter> actParams) {
        super(line, column);
        this.actParams = actParams;
        this.name = name;
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
