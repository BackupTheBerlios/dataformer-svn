/*
 * Created on 02/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * @author mtomcany
 */
public class LongLiteralExpression extends StringLiteralExpression {

    public LongLiteralExpression(int line, int column, String value) {
        super(line, column, value);
    }

    public final boolean isMinValue() {
        return value != null && //
                value.length() == 20 && //
                value.startsWith("9223372036854775808") && //
                (value.charAt(19) == 'L' || value.charAt(19) == 'l');
    }
    
    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
