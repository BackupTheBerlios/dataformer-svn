/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.symbol.Symbol;


/**
 * Represents various types of names:
 * - package names in import
 * - parts of qualified names when using selectors or full qualification
 * 
 * @author mtomcany
 */
public class NameExpression extends Expression {

    public String name;
    public Symbol symbol;

    public NameExpression(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    @Override
    public void accept(NodeVisitor v) {
    	v.visit(this);
    }

}
