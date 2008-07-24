package cz.dataformer.ast.record;

import java.util.List;

import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.PackageDeclaration;
import cz.dataformer.ast.TopLevelASTNode;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.GraphCompilerException;

public class RecordDeclaration extends TopLevelASTNode{

	public List<FieldDeclaration> fields;
	public List<NameExpression> extend;
	
	public RecordDeclaration(int line, int column, Modifiers mods, String name, List<FieldDeclaration> fields, List<NameExpression> extend) {
		this(line,column,mods,name,fields, extend,null,null);
	}
	
	public RecordDeclaration(int line, int column, Modifiers mods, String name, List<FieldDeclaration> fields, List<NameExpression> extend, PackageDeclaration pkg, List<ImportDeclaration> imports) {
		super(line, column,mods,name,pkg,imports);
		this.fields = fields;
		this.extend = extend;
	}

	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}
}
