package cz.dataformer.compiler;

import java.io.File;
import java.util.EnumSet;

import cz.dataformer.ast.Transformation;
import cz.dataformer.compiler.model.TransformationModel;
import cz.dataformer.compiler.symbol.SymbolFlags;
import cz.dataformer.compiler.symbol.TransformationSymbol;

public class XformEntry {

	/** Fully qualified name */
	private String fqn;
	
	/** Resolved file for this transformation */
	private File resolvedPath;
	
	/** Parsed AST tree root */
	private Transformation ast;

	/** Top-level entry for the transformation model */
	private TransformationModel model;
	
	public XformEntry(String fqn, File resolvedPath, Transformation ast) {
		this.fqn = fqn;
		this.resolvedPath = resolvedPath;
		this.ast = ast;
	}


	public Transformation getAst() {
		return ast;
	}
	
	public void setModel(TransformationModel model) {
		this.model = model;
	}
	
	public TransformationModel getModel() {
		return model;
	}

	public String getFqn() {
		return fqn;
	}


	public File getResolvedPath() {
		return resolvedPath;
	}
	
	
	/**
	 * Indication if this transformation entry represents and
	 * errorneous entry (due to syntactic problems or missing file).
	 * 
	 * @return true 	if in error
	 */
	public boolean isInError() {
		return ast == null;
	}
	
}
