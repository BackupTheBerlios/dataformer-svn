/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.body;

import cz.dataformer.NamedDataFormerNode;

/**
 * @author mtomcany
 */
public abstract class BodyDeclaration extends NamedDataFormerNode {

    public BodyDeclaration(int line, int column, String name) {
        super(line, column, name);
    }
    
    
    

}
