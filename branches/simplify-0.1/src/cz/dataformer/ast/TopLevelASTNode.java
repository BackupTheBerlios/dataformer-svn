package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.body.Modifiers;

public abstract class TopLevelASTNode extends NamedDataFormerNode {

	public Modifiers modifiers;
	public PackageDeclaration pkg;
	public List<ImportDeclaration> imports;

	public TopLevelASTNode(int line, int column, Modifiers mods, String name, PackageDeclaration pkg, List<ImportDeclaration> imports) {
		super(line, column, name);
		this.modifiers = mods;
		this.pkg = pkg;
		this.imports = imports;
	}

}
