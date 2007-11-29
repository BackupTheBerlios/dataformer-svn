/*
 * Created on 05/10/2006
 */
package cz.dataformer.body;

import java.util.List;

import cz.dataformer.expr.NameExpression;
import cz.dataformer.statement.BlockStatement;
import cz.dataformer.type.Type;

/**
 * Method declaration
 * @author mtomcany
 */
public final class MethodDeclaration extends BodyDeclaration {

    public final Modifiers modifiers;

    public final Type type;

    public final String name;

    public final List<Parameter> parameters;

    public final List<NameExpression> throws_;

    public final BlockStatement block;

    public MethodDeclaration(int line, int column, Modifiers modifiers, Type returnType, String name, List<Parameter> parameters, List<NameExpression> throws_, BlockStatement block) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = returnType;
        this.name = name;
        this.parameters = parameters;
        this.throws_ = throws_;
        this.block = block;
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
