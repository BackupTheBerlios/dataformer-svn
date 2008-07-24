/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.GraphCompilerException;

/**
 * Method declaration
 * @author mtomcany
 */
public class MethodDeclaration extends BodyDeclaration {

    public Modifiers modifiers;
    /** Return type is ONLY null for constructor */
    public Type returnType;
    public List<Parameter> parameters;
    public List<NameExpression> throws_;
    public BlockStatement block;
    
    public MethodDeclaration(int line, int column, Modifiers modifiers, Type returnType, String name, List<Parameter> parameters, List<NameExpression> throws_, BlockStatement block) {
        super(line, column, name);
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.parameters = parameters;
        this.throws_ = throws_;
        this.block = block;
    }

    @Override
    public void accept(NodeVisitor v) throws GraphCompilerException {
    	v.visit(this);
    }
    
    
    public boolean isConstructor() {
    	return returnType == null;
    }
}
