/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class BlockStatement extends Statement {

    public final List<Statement> statements;

    public BlockStatement(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements= statements;
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
