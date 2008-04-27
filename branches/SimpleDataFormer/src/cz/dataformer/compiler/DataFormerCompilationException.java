package cz.dataformer.compiler;

import cz.dataformer.compiler.symbol.Symbol;

public class DataFormerCompilationException extends Exception {

	private Symbol symbol;
	
	public DataFormerCompilationException(String msg, Symbol symbol) {
		super(msg);
		this.symbol = symbol;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	
}
