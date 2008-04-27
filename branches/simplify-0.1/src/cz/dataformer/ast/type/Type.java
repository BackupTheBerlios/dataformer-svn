package cz.dataformer.ast.type;

import cz.dataformer.DataFormerNode;
import cz.dataformer.compiler.symbol.TypeSymbol;

public abstract class Type extends DataFormerNode {

	public TypeSymbol symbol;
	
	public Type(int line, int column) {
		super(line, column);
	}

}
