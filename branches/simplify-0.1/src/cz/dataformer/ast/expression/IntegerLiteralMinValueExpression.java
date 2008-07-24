/*
 * Created on 09/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * @author mtomcany
 */
public final class IntegerLiteralMinValueExpression extends IntegerLiteralExpression {

    public IntegerLiteralMinValueExpression(int line, int column) {
        super(line, column, "-2147483648");
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
