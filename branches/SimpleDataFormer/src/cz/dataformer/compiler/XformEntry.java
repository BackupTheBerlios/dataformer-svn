package cz.dataformer.compiler;

import java.io.File;
import java.util.EnumSet;

import cz.dataformer.ast.Transformation;
import cz.dataformer.compiler.symbol.SymbolFlags;
import cz.dataformer.compiler.symbol.TransformationSymbol;

public class XformEntry {

	/** Fully qualified name */
	private String fqn;
	
	/** Resolved file for this transformation */
	private File resolvedPath;
	
	/** Parsed AST tree root */
	private Transformation ast;

	/** Top-level entry for the transformation in symbol table */
	private TransformationSymbol symbol;
	
	public XformEntry(String fqn, File resolvedPath, Transformation ast) {
		this.fqn = fqn;
		this.resolvedPath = resolvedPath;
		this.ast = ast;
		this.symbol = new TransformationSymbol(ast,EnumSet.noneOf(SymbolFlags.class));
	}


	public Transformation getAst() {
		return ast;
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
	
	public void setSymbol(TransformationSymbol symbol) {
		this.symbol = symbol;
	}
	
	public TransformationSymbol getSymbol() {
		return symbol;
	}
	
}
