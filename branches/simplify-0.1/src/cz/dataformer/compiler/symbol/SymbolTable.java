package cz.dataformer.compiler.symbol;

import java.util.List;

/**
 * Symbol table implementation. There are two descendants representing the
 * top-level (transformation, imports ) and local scopes (for methods, blocks, etc.)
 * 
 * <p>
 * <b>Implementation note</b><br>
 * Descendants use java.util.Map interfaces for implementation, which do not
 * allow having multiple values stored for one key without use of List or other method.
 * Therefore symbols having the same key = (name,symbolclass) tuple
 * are linked together via {@link Symbol#setNextSameName(Symbol)} and can be
 * iterated. See {@link #put(Symbol)} method implementation for details. 
 * </p>
 * 
 * @author mtomcany
 *
 */
public abstract class SymbolTable {

	/**
	 * Searches for symbol in this symbol table.
	 * If not found, propagates the search to parent's scope.
	 * 
	 * @param symbolName	name to find
	 * @param symbolClass	class to find
	 * @param flags			additional flags to check for
	 * @return	matching symbol, or <code>null</code> if no match
	 */
	public Symbol lookup(String symbolName, SymbolFlags symbolClass, SymbolFlags... flags) {
		// name-match first
		Symbol match = lookup(symbolName);
		
		// find the matching symbol, considering additional flags
		while (match != null && match.getSymbolClass() != symbolClass && !match.isAll(flags)) {
			match = match.getOverload();
		}
		
		return match;
	}
	
	/**
	 * Search for a symbol according to its name
	 * 
	 * @param symbolName	name of symbol to find
	 * @return	symbol or <code>null</code> if no match
	 * @see SymbolTable description for how to process multiple match
	 */
	public abstract Symbol lookup(String symbolName);
	
	/**
	 * Descendants should override for implementation dependent 
	 * storing in their internal structure
	 * 
	 * @param symbol		symbol to store (=lookup data)
	 */
	protected abstract void putInternal(Symbol symbol);
	
	/**
	 * Descendants should override for implementation dependant
	 * storing in their internal structure
	 * 
	 * Overload chaing handing is already implemented in {@link #remove(Symbol)}
	 * 
	 * @param symbol		symbol to delete
	 */
	protected abstract void removeInternal(Symbol symbol);
	
	
	/**
	 * Retrieves all symbols stored in this table. Please note that symbols from
	 * overload chains are not in this listing, since they are stored in different
	 * scopes.
	 * 
	 * List is <b>NOT</b> backed up and required changes must be performed via
	 * {@link #put(Symbol)} and {@link #remove(Symbol)} methods for consistency.
	 * 
	 * @return	all symbols stored in the current symbol table
	 */
	public abstract List<Symbol> symbols();
	
	/**
	 * Inserts new symbol into table.
	 * Links together overloaded symbol (overloading->overloaded)
	 * i.e. symbol having the same (name,type) tuple
	 * as Map-implementation does not allow multiple entries for a single
	 * key. 
	 * 
	 * @param symbolName	symbol name (= lookup key)
	 * @param symbol		symbol to store (= lookup data)
	 */
	public void put(Symbol symbol) throws DuplicateDeclarationException {
		// check for duplicate declaration in this scope
		Symbol dup = lookup(symbol.getName());
		
		if (dup != null && dup.getOwner() == symbol.getOwner()) {
			switch (symbol.getSymbolClass()) {
			case PORT:
				// component: ports cannot clash with properties and fields
				checkCollision(dup,symbol,SymbolFlags.PORT,SymbolFlags.PROPERTY,SymbolFlags.FIELD);
				break;
			case PROPERTY:
				// component: properties cannot clash with ports and fields
				checkCollision(dup,symbol,SymbolFlags.PROPERTY,SymbolFlags.PORT,SymbolFlags.FIELD);
				break;
			case COMPONENT:
				// transformation: components cannot clash with records
				checkCollision(dup,symbol,SymbolFlags.COMPONENT,SymbolFlags.RECORD);
				break;
			case RECORD:
				// transformation: records cannot clash with transformations
				checkCollision(dup,symbol,SymbolFlags.RECORD,SymbolFlags.COMPONENT);
				break;
			case FIELD:
				// record: field cannot clash with other fields
				// component: field cannot clash with ports and properties
				checkCollision(dup,symbol,SymbolFlags.FIELD,SymbolFlags.PORT,SymbolFlags.PROPERTY);
			}
		}
		
		/*
		 * We have:
		 * 1. Possibly overloaded method - FIXME: check method signature
		 * 2. dup == null 
		 * 3. dup is a symbol with the same (name,type) from a different scope
		 * 
		 * Update overloading chain
		 */
		symbol.overload(dup);
		
		// store symbol to current scope, replacing the newest duplicate
		putInternal(symbol);
	}
	
	
	

	public void remove(Symbol toRemove) {
		/*
		 * Symbol may not be stored directly in map because of overload.
		 * 1. Find symbol with the same name
		 * 2. If our symbol - delete our symbol from map and put there second in the chain (if exists)
		 * 3. If other symbol - follow the chain and remove the symbol from there
		 */
		
		Symbol firstInChain = lookup(toRemove.getName());
		
		if (firstInChain == null) {
			return; // at least our symbol to delete should be there, but no match! 
		}
		
		if (firstInChain == toRemove) {
			/*
			 *  Our symbol is first in chain - remove it
			 *  If more in the chain - put follower to the map instead
			 */
			removeInternal(firstInChain);
			Symbol follower = firstInChain.getOverload();
			if (follower != null) {
				putInternal(follower);
			}
			
			return;
		}
		
		/*
		 * Other symbol with the same name. Follow chain to find our symbol and 
		 * remove it keeping consistency in the chain
		 */
		while (firstInChain != null && firstInChain != toRemove) {
			firstInChain=firstInChain.getOverload();
		}
		
		if (firstInChain != null) {
			// match - update chain
			firstInChain.overload(toRemove.getOverload());
		}
		
		removeInternal(toRemove);
		
	}
	
	
	private void checkCollision(Symbol dup, Symbol newSym, SymbolFlags... collisions) 
	throws DuplicateDeclarationException {
		Symbol owner = newSym.getOwner();
		while (dup != null && dup.getOwner() == owner) {
			for (SymbolFlags c : collisions) {
				if (dup.getSymbolClass() == c) {
					throw new DuplicateDeclarationException(newSym,dup);
				}
			}
			dup = dup.getOverload();
		}
		
	}

	
}
