/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;


/**
 * Represents qualified name
 * 
 * @author mtomcany
 */
public final class QualifiedNameExpression extends NameExpression {

    public final NameExpression qualifier;

    public QualifiedNameExpression(int line, int column, NameExpression scope, String name) {
        super(line, column, name);
        this.qualifier = scope;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
