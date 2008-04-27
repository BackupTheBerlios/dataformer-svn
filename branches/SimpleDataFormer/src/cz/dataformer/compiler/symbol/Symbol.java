package cz.dataformer.compiler.symbol;

import java.util.Set;

import cz.dataformer.DataFormerNode;

public class Symbol {
	
	public static final class Key implements Comparable<Key> {
		
		private String name;
		private SymbolFlags sc;
		
		public Key(String name, SymbolFlags sc) {
			this.name = name;
			this.sc = sc;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			
			Key otherKey = (Key)obj;
			if (sc == otherKey.sc) {
				return name == otherKey.name;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return 31*name.hashCode() + sc.hashCode();
		}

		public int compareTo(Key o) {
			int result = this.name.compareTo(o.name);
			if (result == 0) {
				return this.sc.compareTo(o.sc);
			}
			
			return result;
		}
		
		
	}
	
	
	/** AST node related to this symbol */
	protected DataFormerNode ast;

	/** Scope that applies to this symbol */
	protected SymbolTable scope;
	
	/** Name of this symbol */
	protected String name;
	
	/** Class of this symbol e.g. variable, record */
	protected SymbolFlags symbolClass;
	
	/** Various symbol flags */
	protected Set<SymbolFlags> flags;
	
	/**
	 *  Closest overloaded symbol in the overload chain 
	 *  The first symbol of the chain is stored in the symbol table.
	 *  The rest are kept aside as java.util.Map does not support multi-entries
	 *  for the same key 
	 */
	protected Symbol closestOverloaded;
	
	/** Owner of this symbol */
	protected Symbol owner;
	
	
	
	
	public Symbol(DataFormerNode ast, String name, SymbolFlags symbolClass, Symbol owner, Set<SymbolFlags> flags) {
		// must be not null for SymbolTable implementation .equals() and .hashCode()
		assert name != null && symbolClass != null : "Name or symbol class is null";
		
		this.ast = ast;
		this.name = name;
		this.symbolClass = symbolClass;
		this.owner = owner;
		this.flags = flags;
	}
	
	
	public void overload(Symbol closestOverloaded) {
		this.closestOverloaded = closestOverloaded;
	}
	
	public DataFormerNode getAst() {
		return ast;
	}
	
	public Symbol getOverload() {
		return closestOverloaded;
	}
	
	public String getName() {
		return name;
	}
	
	public SymbolFlags getSymbolClass() {
		return symbolClass;
	}
	
	public SymbolTable getScope() {
		return scope;
	}
	
	public Symbol getOwner() {
		return owner;
	}
	
	public Set<SymbolFlags> getFlags() {
		return flags;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj instanceof Symbol) {
			Symbol otherSym = (Symbol)obj;
			
			/*
			 * All comparisons below should be == right now, not .equals
			 * 1. is enum
			 * 2. is string comparison
			 * 3. we need to compare the scopes pointer
			 */
			if (this.symbolClass == otherSym.symbolClass) {
				if (this.name == otherSym.name) {
					return this.scope == otherSym.scope;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*symbolClass.hashCode() + name.hashCode();
	}
	
	
	/**
	 * @return	Key for maps based on symbol class and symbol name 
	 */
	public Key key() {
		return new Key(this.name,this.symbolClass);
	}

	/**
	 * Marks symbol with all specified flags
	 * @param flagsToMark
	 */
	public void flag(SymbolFlags... flagsToMark) {
		for (SymbolFlags f : flagsToMark)
		flags.add(f);
	}
	
	/**
	 * @param flagsToCheck
	 * @return	true when all flags are set on the symbol, false otherwise
	 */
	public boolean isAll(SymbolFlags... flagsToCheck) {
		for (SymbolFlags f : flagsToCheck) {
			if (! flags.contains(f)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @param flagsToCheck
	 * @return	true when at least one flag is set on the symbol, false otherwise
	 */
	public boolean isOneOf(SymbolFlags... flagsToCheck) {
		for (SymbolFlags f : flagsToCheck) {
			if (flags.contains(f)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return symbolClass.description() + "(" + name + ")";
		
	}
}
