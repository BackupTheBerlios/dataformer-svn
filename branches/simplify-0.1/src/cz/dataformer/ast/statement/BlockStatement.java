/*
 * Created on 04/11/2006
 */
package cz.dataformer.ast.statement;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class BlockStatement extends Statement {

    public  List<Statement> statements;

    public BlockStatement(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements= statements;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
}
