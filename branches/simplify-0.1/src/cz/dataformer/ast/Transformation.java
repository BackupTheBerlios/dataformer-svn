package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.statement.ConnectStatement;
import cz.dataformer.compiler.GraphCompilerException;

public class Transformation extends TopLevelASTNode {

	public List<ComponentDeclaration> components;
	public List<RecordDeclaration> records;
	public List<TransformationFieldDeclaration> variables;
	public List<ConnectStatement> graph;

	public Transformation(int line, int column, Modifiers mods, String name, List<ComponentDeclaration> components, List<RecordDeclaration> records, List<TransformationFieldDeclaration> variables, List<ConnectStatement> graph, PackageDeclaration pkg, List<ImportDeclaration> imports) {
		super(line, column, mods,name,pkg,imports);
		this.components = components;
		this.records = records;
		this.variables = variables;
		this.graph = graph;
	}

	public void accept(NodeVisitor v) throws GraphCompilerException {
		v.visit(this);
	}
	
}
