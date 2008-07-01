package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.statement.ConnectStatement;

public class Transformation extends NamedDataFormerNode {

	public Modifiers modifiers;
	public List<ComponentDeclaration> components;
	public List<RecordDeclaration> records;
	public List<TransformationFieldDeclaration> variables;
	public List<ConnectStatement> graph;
	public PackageDeclaration packageName;
	public List<ImportDeclaration> imports;

	public Transformation(int line, int column, Modifiers mods, String name, List<ComponentDeclaration> components, List<RecordDeclaration> records, List<TransformationFieldDeclaration> variables, List<ConnectStatement> graph, PackageDeclaration packageName, List<ImportDeclaration> imports) {
		super(line, column, name);
		this.modifiers = mods;
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
