/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast.expression;

import java.util.List;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.type.Type;

/**
 * @author mtomcany
 */
public final class ArrayAllocationExpression extends Expression {

    public Type type;
    public int arrayCount;
    public ArrayInitializerExpression initializer;
    public List<Expression> dimensions;

    public ArrayAllocationExpression(int line, int column, Type type, int arrayCount, ArrayInitializerExpression initializer) {
        super(line, column);
        this.type = type;
        this.arrayCount = arrayCount;
        this.initializer = initializer;
        this.dimensions = null;
    }

    public ArrayAllocationExpression(int line, int column, Type type, List<Expression> dimensions, int arrayCount) {
        super(line, column);
        this.type = type;
        this.arrayCount = arrayCount;
        this.dimensions = dimensions;
        this.initializer = null;
    }

	@Override
	public void accept(NodeVisitor v) {
		v.visit(this);
	}


}
