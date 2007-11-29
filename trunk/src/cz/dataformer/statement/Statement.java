/*
 * Created on 03/11/2006
 */
package cz.dataformer.statement;

import cz.dataformer.DataFormerNode;

/**
 * Parent of all statement types
 * @author mtomcany
 */
public abstract class Statement extends DataFormerNode {

    public Statement(int line, int column) {
        super(line, column);
    }

}
