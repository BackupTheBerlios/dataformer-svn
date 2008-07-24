package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.type.ComponentType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.compiler.GraphCompilerException;

public class ComponentDeclaration extends TopLevelASTNode {

	
	public List<IOTypeParameter> ioParams;
	public List<BodyDeclaration> members;
	public MainBlock main;
	public ComponentType extend;
	public PackageDeclaration pkg;
	public List<ImportDeclaration> imports;

	public ComponentDeclaration(int line, int column, Modifiers mods, String name, List<IOTypeParameter> ioParams, List<BodyDeclaration> members, MainBlock main, ComponentType extend) {
		this(line,column,mods,name,ioParams,members,main,extend,null,null);
	}

	public ComponentDeclaration(int line, int column, Modifiers mods, String name, List<IOTypeParameter> ioParams, List<BodyDeclaration> members, MainBlock main, ComponentType extend, PackageDeclaration pkg, List<ImportDeclaration> imports) {
		super(line, column, mods,name,pkg,imports);
		this.ioParams = ioParams;
		this.members = members;
		this.main = main;
		this.extend = extend;
	}
	
	@Override
	public void accept(NodeVisitor v) throws GraphCompilerException  {
		v.visit(this);
	}
}
