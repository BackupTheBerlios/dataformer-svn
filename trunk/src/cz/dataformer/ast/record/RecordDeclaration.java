package cz.dataformer.ast.record;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.ast.body.Modifiers;

public class RecordDeclaration extends DataFormerNode {

	public Modifiers modifiers;
	public String name;
	public List<FieldDeclaration> fields;

	public RecordDeclaration(int line, int column, Modifiers mods, String name, List<FieldDeclaration> fields) {
		super(line, column);
		this.modifiers = mods;
		this.name = name;
		this.fields = fields;
	}

	public void accept(NodeVisitor v) {
		v.visit(this);
	}
}
