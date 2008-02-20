/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.expression.NameExpression;

/**
 * @author mtomcany
 */
public final class ImportDeclaration extends DataFormerNode {

    public final NameExpression name;

    public final boolean isAsterisk;

    public ImportDeclaration(int line, int column, NameExpression name, boolean isAsterisk) {
        super(line, column);
        this.name = name;
        this.isAsterisk = isAsterisk;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
