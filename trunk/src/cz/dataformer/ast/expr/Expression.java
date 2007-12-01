/*
 * Created on 10/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.DataFormerNode;

/**
 * Root of all expression nodes
 * @author mtomcany
 *
 */
public abstract class Expression extends DataFormerNode {

    public Expression(int line, int column) {
        super(line, column);
    }

}
