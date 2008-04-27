/*
 * Created on 03/11/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.symbol.VariableSymbol;

/**
 * Method or catch-clause parameter
 * 
 * @author mtomcany
 */
public final class Parameter extends DataFormerNode {

    public Modifiers modifiers;
    public Type type;
    public boolean isVarArgs;
    public VariableDeclaratorId id;
    public VariableSymbol symbol;
    
    public Parameter(int line, int column, Modifiers modifiers, Type type, boolean isVarArgs, VariableDeclaratorId id) {
        super(line, column);
        this.modifiers = modifiers;
        this.type = type;
        this.isVarArgs = isVarArgs;
        this.id = id;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
