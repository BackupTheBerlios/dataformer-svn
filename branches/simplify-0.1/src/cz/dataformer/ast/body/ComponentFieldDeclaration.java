/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.symbol.VariableSymbol;

/**
 * @author mtomcany
 */
public final class ComponentFieldDeclaration extends BodyDeclaration {

    public Modifiers modifiers;
    public Type type;
    public VariableDeclarator variable;
    public VariableSymbol symbol;

    public ComponentFieldDeclaration(int line, int column, Modifiers modifiers, Type type, VariableDeclarator variable) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = type;
        this.variable = variable;
    }

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
