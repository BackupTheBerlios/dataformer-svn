/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * @author mtomcany
 */
public final class ComponentFieldDeclaration extends BodyDeclaration {

    public Modifiers modifiers;
    public Type type;
    public VariableDeclarator variable;

    public ComponentFieldDeclaration(int line, int column, Modifiers modifiers, Type type, VariableDeclarator variable) {
        super(line, column, variable.id);
        this.modifiers = modifiers;
        this.type = type;
        this.variable = variable;
    }

	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}
}
