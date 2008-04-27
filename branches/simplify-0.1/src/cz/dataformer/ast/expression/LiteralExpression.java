/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

/**
 * @author mtomcany
 */
public abstract class LiteralExpression extends Expression {

    public LiteralExpression(int line, int column) {
        super(line, column);
    }
}
