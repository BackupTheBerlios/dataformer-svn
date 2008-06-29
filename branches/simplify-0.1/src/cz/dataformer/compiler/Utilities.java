package cz.dataformer.compiler;

import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.expression.QualifiedNameExpression;

public final class Utilities {

	private Utilities() {
		// not available
	}

	public static String nameToString(NameExpression nameExp) {
		StringBuilder buf = new StringBuilder();
		while (nameExp instanceof QualifiedNameExpression) {
			buf.append(nameExp.name).append(".");
			nameExp = ((QualifiedNameExpression)nameExp).qualifier;
		}
		// this handles the last non-qualified name expression
		buf.append(nameExp.name);
		
		return buf.toString();
	}

}
