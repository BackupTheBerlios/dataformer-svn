package cz.dataformer.compiler.symbol;

import java.util.Set;

import cz.dataformer.DataFormerNode;

public class RecordSymbol extends TypeSymbol {

	
	public RecordSymbol(DataFormerNode ast, String name, Symbol owner, Set<SymbolFlags> flags) {
		super(ast, name, owner, flags);
		flag(SymbolFlags.RECORD);
		
		assert owner != null && owner.getScope() != null;
		this.scope = new LocalSymbolTable(owner.getScope());
	}
	

}
