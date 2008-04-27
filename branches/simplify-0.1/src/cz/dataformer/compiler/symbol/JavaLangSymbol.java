package cz.dataformer.compiler.symbol;

import java.util.EnumSet;

/**
 * Dummy symbol used as origin for Java symbols
 * such as primitive types.
 * 
 * @author mtomcany
 *
 */
public final class JavaLangSymbol extends Symbol {

	public static final JavaLangSymbol INSTANCE = new JavaLangSymbol();
	
	public JavaLangSymbol() {
		super(null, "java.lang.Dummy", SymbolFlags.TRANSFORMATION, null,EnumSet.of(SymbolFlags.PUBLIC));
	}
	
}
