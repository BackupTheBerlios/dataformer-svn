/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;


/**
 * @author mtomcany
 */
public final class EmptyStatement extends Statement {

    public EmptyStatement(int line, int column) {
        super(line, column);
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
