package cz.dataformer.compiler.symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Local symbol table implementation.
 * Number of symbols in local scopes is much lower than in the global scope
 * (containing all imported symbols).
 * Hashing overhead might be therefore too high, so we opted for tree based
 * implementation working in O(log(n)) time guaranteed by {@link TreeMap}
 * 
 * @author mtomcany
 *
 */
public class LocalSymbolTable extends SymbolTable {

	private SymbolTable parentScope;
	
	/**
	 * Data store for symbols
	 * Lazy allocation when the first symbol is entered
	 */
	private TreeMap<String,Symbol> table;
	
	public LocalSymbolTable(SymbolTable parentScope) {
		this.parentScope = parentScope;
	}
	
	/**
	 * Lookup in local scope. 
	 * If no match, search is delegated to parent scope.
	 */
	@Override
	public Symbol lookup(String symbolName) {
		Symbol match = null;
		
		if (table != null) {
			match = table.get(symbolName);
		}
		
		assert parentScope != null; // all local scopes must have parent scope set
		if (match == null) {
			return parentScope.lookup(symbolName);
		}
		
		return match;
	}

	@Override
	protected void putInternal(Symbol symbol) {
		if (table == null) {
			table = new TreeMap<String,Symbol>();
		}
		table.put(symbol.getName(),symbol);
	}
	
	@Override
	protected void removeInternal(Symbol symbol) {
		if (table == null) {
			// not yet allocated and removing? weeeeird :)
			return;
		}
		
		table.remove(symbol.getName());
	}
	
	@Override
	public List<Symbol> symbols() {
		return table == null ? Collections.<Symbol>emptyList() : new ArrayList<Symbol>(table.values());
	}
	
}
