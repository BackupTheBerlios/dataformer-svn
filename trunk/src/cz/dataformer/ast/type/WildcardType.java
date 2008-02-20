/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.type;

import cz.dataformer.ast.NodeVisitor;

/**
 * @author mtomcany
 *
 */
public final class WildcardType extends Type {

    public ReferenceType ext;
    public ReferenceType sup;

    public WildcardType(int line, int column, ReferenceType ext, ReferenceType sup) {
        super(line, column);
        assert ext == null || sup == null;
        this.ext = ext;
        this.sup = sup;
    }

    @Override
    public boolean isPrimitive() {
    	return false;
    }
    
    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }
}
