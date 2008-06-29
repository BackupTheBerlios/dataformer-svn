package cz.dataformer.ast.record;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.compiler.symbol.RecordSymbol;

public class RecordDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public String name;
	public List<FieldDeclaration> fields;
	public RecordSymbol symbol;
	public List<NameExpression> extend;
	
	public RecordDeclaration(int line, int column, Modifiers mods, String name, List<FieldDeclaration> fields, List<NameExpression> extend) {
		super(line, column);
		this.modifiers = mods;
		this.name = name;
		this.fields = fields;
		this.extend = extend;
	}

	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
