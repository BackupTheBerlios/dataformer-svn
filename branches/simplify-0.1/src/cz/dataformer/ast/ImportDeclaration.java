/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast;

import cz.dataformer.NamedDataFormerNode;

/**
 * @author mtomcany
 */
public final class ImportDeclaration extends NamedDataFormerNode {

    public final boolean isAsterisk;

    public ImportDeclaration(int line, int column, String name, boolean isAsterisk) {
        super(line, column, name);
        this.isAsterisk = isAsterisk;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
