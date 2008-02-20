/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;


/**
 * @author mtomcany
 */
public class StringLiteralExpression extends LiteralExpression {

    public String value;

    public StringLiteralExpression(int line, int column, String value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
    
}
