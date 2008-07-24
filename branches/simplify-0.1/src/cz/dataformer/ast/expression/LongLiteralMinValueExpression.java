/*
 * Created on 09/03/2007
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


/**
 * @author mtomcany
 */
public final class LongLiteralMinValueExpression extends LongLiteralExpression {

    public LongLiteralMinValueExpression(int line, int column) {
        super(line, column, "-9223372036854775808L");
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }

}
