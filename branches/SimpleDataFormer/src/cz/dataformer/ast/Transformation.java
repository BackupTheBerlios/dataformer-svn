package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.statement.ConnectStatement;

public class Transformation extends DataFormerNode {

	public Modifiers modifiers;
	public String name;
	public List<ComponentDeclaration> components;
	public List<RecordDeclaration> records;
	public List<ComponentVariableDeclaration> variables;
	public List<ConnectStatement> graph;
	public NameExpression packageName;
	public List<ImportDeclaration> imports;

	public Transformation(int line, int column, Modifiers mods, String name, List<ComponentDeclaration> components, List<RecordDeclaration> records, List<ComponentVariableDeclaration> variables, List<ConnectStatement> graph, NameExpression packageName, List<ImportDeclaration> imports) {
		super(line, column);
		this.modifiers = mods;
		this.name = name;
		this.components = components;
		this.records = records;
		this.variables = variables;
		this.graph = graph;
		this.packageName = packageName;
		this.imports = imports;
	}

	public void accept(NodeVisitor v) {
		v.visit(this);
	}
	
}
