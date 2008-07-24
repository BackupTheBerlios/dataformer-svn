/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


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
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
    
}
