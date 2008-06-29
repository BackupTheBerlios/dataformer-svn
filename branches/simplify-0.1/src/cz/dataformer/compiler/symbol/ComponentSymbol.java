package cz.dataformer.compiler.symbol;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.Type;


public class ComponentSymbol extends TypeSymbol {

	public ComponentSymbol(ComponentDeclaration ast, Symbol owner, Set<SymbolFlags> flags) {
		super(ast, ast.name, owner, flags);
		flag(SymbolFlags.COMPONENT);
		
		assert owner != null && owner.getScope() != null;
		this.scope = new LocalSymbolTable(owner.getScope());
	}
	
	/**
	 * Creates type specific instance from a generic component declaration.
	 * Generic types are bound to specific record types.
	 * The resulting component symbol is stored only with the given variable
	 * and not put into the symbol table (would be treated duplicate against generic component)
	 * 
	 * The method does not do any validations on correct number of types and their
	 * resolution, so this must be done prior to calling this method
	 * 
	 */
	public ComponentSymbol instantiate(List<NameExpression> replacements) {
		ComponentDeclaration genericAST = (ComponentDeclaration)getAst();
		
		// copy all flags except 'generic' 
		Set<SymbolFlags> flags = EnumSet.copyOf(getFlags());
		flags.remove(SymbolFlags.GENERIC);
		
		// create specific instance with an empty scope
		ComponentSymbol instance = new ComponentSymbol(genericAST,getOwner(),flags);
		
		// substitute types - number of types should have been checked before calling this
		Iterator<NameExpression> replaceIter = replacements.iterator();

		// build the type translation table
		HashMap<String,TypeSymbol> transTable = new HashMap<String,TypeSymbol>();
		assert replacements.size() == genericAST.ioParams.size() : "Insufficient number of types. Was it validated?!";
		for (IOTypeParameter io : genericAST.ioParams) {
			transTable.put(io.name,(TypeSymbol)replaceIter.next().symbol);
		}
		
		// substitute the types in variables and methods using the translation table
		for (Symbol candidate : getScope().symbols()) {
			// TODO: when component extending is in place, we need to substitute parent's symbols as well
			Symbol substSymbol = null;
			if (candidate.isAll(SymbolFlags.GENERIC)) {
				if (candidate instanceof VariableSymbol) {
					VariableSymbol vs = (VariableSymbol)candidate;
					substSymbol = vs.instantiate(transTable);
				} else if (candidate instanceof MethodSymbol) {
					MethodSymbol origSym = (MethodSymbol)candidate;
					substSymbol = origSym.instantiate(transTable);
				} else {
					// all other kinds of symbols are just copied verbatim
					substSymbol = candidate;
				}
			} else {
				// non-generic symbols are just copied verbatim
				substSymbol = candidate;
			}
			
			try {
				instance.getScope().put(substSymbol);
			} catch (DuplicateDeclarationException e) {
				// no duplicates in generic scope -> no duplicates in this scope
				assert false : "Unreachable code";
			}
		}
		
		return instance;
		
	}

}
