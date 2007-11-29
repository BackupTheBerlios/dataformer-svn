/*
 * Created on 03/11/2006
 */
package cz.dataformer.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.type.Type;

/**
 * Method parameter
 * @author mtomcany
 */
public final class Parameter extends DataFormerNode {

    public final Modifiers modifiers;
    public final Type type;
    public final boolean isVarArgs;
    public final VariableDeclaratorId id;

    public Parameter(int line, int column, Modifiers modifiers, Type type, boolean isVarArgs, VariableDeclaratorId id) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = type;
        this.isVarArgs = isVarArgs;
        this.id = id;
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
