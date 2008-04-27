/*
 * Created on 03/11/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.body.VariableDeclarator;
import cz.dataformer.ast.type.Type;

/**
 * @author mtomcany
 */
public final class VariableDeclarationExpression extends Expression {

    public Modifiers modifiers;
    public Type type;
    public List<VariableDeclarator> vars;

    public VariableDeclarationExpression(int line, int column, Modifiers modifiers, Type type, List<VariableDeclarator> vars) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = type;
        this.vars = vars;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
