/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;


/**
 * @author mtomcany
 */
public final class BooleanLiteralExpression extends LiteralExpression {

    public Boolean value;

    public BooleanLiteralExpression(int line, int column, Boolean value) {
        super(line, column);
        this.value = value;
    }
    
    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
