/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;


/**
 * @author mtomcany
 */
public final class FieldAccessExpression extends Expression {

    public Expression scope;
    public String field;
   

    public FieldAccessExpression(int line, int column, Expression scope, String field) {
        super(line, column);
        this.scope = scope;
        this.field = field;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
