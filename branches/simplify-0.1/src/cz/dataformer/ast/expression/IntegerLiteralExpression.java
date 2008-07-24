/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * @author mtomcany
 */
public class IntegerLiteralExpression extends StringLiteralExpression {

    public IntegerLiteralExpression(int line, int column, String value) {
        super(line, column, value);
    }

    public final boolean isMinValue() {
        return value != null && //
                value.length() == 10 && //
                value.equals("2147483648");
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
