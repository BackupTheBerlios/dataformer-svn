package cz.dataformer.compiler.symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GlobalSymbolTable extends SymbolTable {

	private HashMap<String,Symbol> table = new HashMap<String,Symbol>(100);

	
	
	@Override
	public Symbol lookup(String symbolName) {
		return table.get(symbolName);
	}
	
	
	@Override
	protected void putInternal(Symbol symbol) {
		table.put(symbol.getName(),symbol);
	}
	
	@Override
	protected void removeInternal(Symbol symbol) {
		table.remove(symbol.getName());
	}

	@Override
	public List<Symbol> symbols() {
		return new ArrayList<Symbol>(table.values());
	}

}
