/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.AssignOperatorEnum;


/**
 * @author mtomcany
 */
public final class AssignmentExpression extends Expression {

    public final Expression target;
    public final Expression value;
    public final AssignOperatorEnum op;

    public AssignmentExpression(int line, int column, Expression target, Expression value, AssignOperatorEnum op) {
        super(line, column);
        this.target = target;
        this.value = value;
        this.op = op;
    }

//    @Override
//    public <A> void accept(VoidVisitor<A> v, A arg) {
//        v.visit(this, arg);
//    }
//
//    @Override
//    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
//        return v.visit(this, arg);
//    }

}
