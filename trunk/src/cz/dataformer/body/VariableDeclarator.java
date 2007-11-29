/*
 * Created on 05/10/2006
 */
package cz.dataformer.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.expr.Expression;

/**
 * @author mtomcany
 */
public final class VariableDeclarator extends DataFormerNode {

    public final VariableDeclaratorId id;

    public final Expression init;

    public VariableDeclarator(int line, int column, VariableDeclaratorId id, Expression init) {
        super(line, column);
        this.id = id;
        this.init = init;
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
