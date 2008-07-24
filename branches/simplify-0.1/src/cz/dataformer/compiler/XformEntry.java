package cz.dataformer.compiler;

import java.io.File;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.TopLevelASTNode;
import cz.dataformer.compiler.model.TopLevelModel;
import cz.dataformer.compiler.model.TopLevelModel.TopLevel;

public class XformEntry {

	/** Resolved file for this transformation */
	private File resolvedPath;
	
	/** Parsed AST tree root */
	private DataFormerNode ast;

	/** Top-level entry for corresponding model */
	private TopLevelModel model;

	/** Flag if this is an erroneous entry due to errors in parsing or model */
	private boolean inError;

	/** Top level type of this entry */
	private final TopLevel topLevel;
	
	public XformEntry(File resolvedPath, DataFormerNode ast, TopLevel topLevel) {
		this.resolvedPath = resolvedPath;
		this.ast = ast;
		this.topLevel = topLevel;
		inError = ast == null;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DataFormerNode> T getAst() {
		return (T)ast;
	}
	
	public void setModel(TopLevelModel model) {
		this.model = model;
	}
	
	@SuppressWarnings("unchecked")
	public <M extends TopLevelModel> M getModel() {
		return (M)model;
	}

	public File getResolvedPath() {
		return resolvedPath;
	}
	
	public String getFQDN() {
		TopLevelASTNode ast = getAst();
		return ast.pkg.name + "." + ast.name;
	}
	
	public TopLevel getTopLevelKind() {
		return topLevel;
	}
	
	/**
	 * Indication if this transformation entry represents and
	 * erroneous entry (due to syntactic problems or missing file).
	 * 
	 * @return true 	if in error
	 */
	public boolean isInError() {
		return inError;
	}
	
	public void setInError() {
		this.inError = true;
	}
	
}
