/*
 * Created on 10/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.DataFormerNode;
import cz.dataformer.compiler.symbol.TypeSymbol;

/**
 * Root of all expression nodes
 * @author mtomcany
 *
 */
public abstract class Expression extends DataFormerNode {

	/** Type this expression resolves to during semantic analysis */
	public TypeSymbol resolvedType;
	
    public Expression(int line, int column) {
        super(line, column);
    }

}
