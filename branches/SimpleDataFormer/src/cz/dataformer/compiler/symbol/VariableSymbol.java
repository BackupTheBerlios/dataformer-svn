package cz.dataformer.compiler.symbol;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import cz.dataformer.DataFormerNode;

/**
 * Symbol representing record fields, ports, properties and local variables
 * Check {@link Symbol#symbolClass} for specific type.
 * 
 * @author mtomcany
 *
 */
public class VariableSymbol extends Symbol {

	private TypeSymbol type;
	
	public VariableSymbol(DataFormerNode ast, String name, SymbolFlags symbolClass,  Symbol owner, TypeSymbol type, Set<SymbolFlags> flags) {
		super(ast, name, SymbolFlags.VARIABLE, owner, flags);
		this.type = type;
	}
	
	public TypeSymbol getType() {
		return type;
	}

	public VariableSymbol instantiate(Map<String,TypeSymbol> transTable) {
		TypeSymbol substType = transTable.get(type.getName());
		EnumSet<SymbolFlags> newFlags = EnumSet.copyOf(getFlags());
		newFlags.remove(SymbolFlags.GENERIC);
		return new VariableSymbol(getAst(),getName(),getSymbolClass(),getOwner(),substType,newFlags);
	}
	
}
