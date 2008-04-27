/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.AssignOperatorEnum;
import cz.dataformer.ast.NodeVisitor;


/**
 * @author mtomcany
 */
public final class AssignmentExpression extends Expression {

    public Expression target;
    public Expression value;
    public AssignOperatorEnum op;

    public AssignmentExpression(int line, int column, Expression target, Expression value, AssignOperatorEnum op) {
        super(line, column);
        this.target = target;
        this.value = value;
        this.op = op;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
