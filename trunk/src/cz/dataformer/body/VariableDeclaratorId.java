/*
 * Created on 05/10/2006
 */
package cz.dataformer.body;

import cz.dataformer.DataFormerNode;

/**
 * Variable declarator 
 * @author mtomcany
 */
public final class VariableDeclaratorId extends DataFormerNode {

    public final String name;
    public final int arrayCount;

    public VariableDeclaratorId(int line, int column, String name, int arrayCount) {
        super(line, column);
        this.name = name;
        this.arrayCount = arrayCount;
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
