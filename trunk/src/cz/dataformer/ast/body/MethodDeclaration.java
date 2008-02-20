/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.ast.type.Type;

/**
 * Method declaration
 * @author mtomcany
 */
public final class MethodDeclaration extends BodyDeclaration {

    public Modifiers modifiers;

    public Type type;

    public String name;

    public List<Parameter> parameters;

    public List<NameExpression> throws_;

    public BlockStatement block;

    public MethodDeclaration(int line, int column, Modifiers modifiers, Type returnType, String name, List<Parameter> parameters, List<NameExpression> throws_, BlockStatement block) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = returnType;
        this.name = name;
        this.parameters = parameters;
        this.throws_ = throws_;
        this.block = block;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
