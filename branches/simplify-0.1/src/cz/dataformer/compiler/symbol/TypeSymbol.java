package cz.dataformer.compiler.symbol;

import java.util.EnumSet;
import java.util.Set;

import cz.dataformer.DataFormerNode;

public class TypeSymbol extends Symbol {

	/**
	 * Symbol for void type
	 */
	public static final TypeSymbol VOID = new TypeSymbol(null,"void",JavaLangSymbol.INSTANCE,EnumSet.noneOf(SymbolFlags.class));
	
	/**
	 * Symbol for error type
	 */
	public static final TypeSymbol ERROR = new TypeSymbol(null,"<error>",JavaLangSymbol.INSTANCE,EnumSet.noneOf(SymbolFlags.class));
	
	
	public TypeSymbol(DataFormerNode ast, String name, Symbol owner, Set<SymbolFlags> flags) {
		super(ast, name, SymbolFlags.TYPE, owner, flags);
	}

}
