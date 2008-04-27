package cz.dataformer.compiler.symbol;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import cz.dataformer.DataFormerNode;

public class MethodSymbol extends Symbol {

	private LinkedList<TypeSymbol> signature;
	private TypeSymbol returnSymbol;
	
	public MethodSymbol(DataFormerNode ast, String name, Symbol owner, Set<SymbolFlags> flags) {
		super(ast, name, SymbolFlags.METHOD, owner, flags);
		assert owner != null && owner.getScope() != null;
		this.scope = new LocalSymbolTable(owner.getScope());
	}

	public void setSignature(LinkedList<TypeSymbol> signature) {
		this.signature = signature;
	}
	
	public void setReturnSymbol(TypeSymbol returnSymbol) {
		this.returnSymbol = returnSymbol;
	}

	public MethodSymbol instantiate(Map<String,TypeSymbol> transTable) {
		EnumSet<SymbolFlags> flags = EnumSet.copyOf(getFlags());
		flags.remove(SymbolFlags.GENERIC);
		MethodSymbol instance = new MethodSymbol(getAst(),getName(),getOwner(),flags);

		// substitute return type if neccessary
		if (returnSymbol != TypeSymbol.VOID && returnSymbol.isAll(SymbolFlags.GENERIC)) {
			instance.setReturnSymbol(transTable.get(returnSymbol.getName()));
		}
		
		// substitute all generic symbols in scope of this method
		for (Symbol candidate : getScope().symbols()) {
			Symbol substSymbol = null;
			if (candidate.isAll(SymbolFlags.GENERIC) 
				&& candidate instanceof VariableSymbol) {
				VariableSymbol vs = (VariableSymbol)candidate;
				substSymbol = vs.instantiate(transTable);
			} else {
				substSymbol = candidate;
			}
			
			try {
				instance.getScope().put(substSymbol);
			} catch (DuplicateDeclarationException e) {
				assert false : "Unreachable code";
			}
		}
		
		return instance;
	}
}
