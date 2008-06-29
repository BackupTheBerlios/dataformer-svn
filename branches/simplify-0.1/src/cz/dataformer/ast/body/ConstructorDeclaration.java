package cz.dataformer.ast.body;

import java.util.List;

import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.ast.type.VoidType;

public class ConstructorDeclaration extends MethodDeclaration {

	public ConstructorDeclaration(int line, int column, Modifiers modifiers, List<Parameter> parameters, List<NameExpression> throwsDeclaration, BlockStatement block) {
		super(line,column,modifiers,new VoidType(line,column),"<init>",parameters,throwsDeclaration,block);
	}

}
