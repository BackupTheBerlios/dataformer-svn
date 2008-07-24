package cz.dataformer.compiler.model;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;

import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.compiler.CompilerEnvironment;
import cz.dataformer.compiler.DefaultsAndConstants;
import cz.dataformer.compiler.FileManager;
import cz.dataformer.compiler.GraphCompilerException;
import cz.dataformer.compiler.ParserWrapper;
import cz.dataformer.compiler.XformEntry;
import cz.dataformer.compiler.model.TopLevelModel.TopLevel;

public class ImportModel extends ModelNode implements NamedModelNode {

	private final CompilerEnvironment env = CompilerEnvironment.getInstance();
	private File importRoot;
	private HashSet<String> contents;
	
	
	public ImportModel(ImportDeclaration ast, TopLevelModel owner) {
		super(ast, owner);
	}

	public boolean isStar() {
		return ((ImportDeclaration) ast).isStar;
	}

	public String name() {
		ImportDeclaration id = getAst();
		return id.isStar ? id.prefix : id.prefix + "." + id.simpleName;
	}

	public String getSimpleName() {
		ImportDeclaration id = getAst();
		return id.simpleName;
	}

	public String getPrefix() {
		ImportDeclaration id = getAst();
		return id.prefix;
	}

	public void populate() throws GraphCompilerException {
		FileManager fm = FileManager.getInstance();
		importRoot = fm.getSourcePathEntry(name());
		if (importRoot == null) {
			throw new GraphCompilerException(name() + " does not resolve to a valid source path");
		}
		
		if (isStar()) {
			String files[] = importRoot.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(DefaultsAndConstants.GRAPH_FILE_SUFFIX); 
				}
			}); 
			
			contents = new HashSet<String>(files.length);
			
			// store existing entries
			int stripLen = DefaultsAndConstants.GRAPH_FILE_SUFFIX.length() + 1;
			for (String f : files) {
				contents.add(f.substring(0, f.length() - stripLen));
			}
		} else {
			// single content
			String impFileName = getSimpleName() + "." + DefaultsAndConstants.GRAPH_FILE_SUFFIX;
			File f = new File(importRoot,impFileName);
			if (!f.exists()) {
				throw new GraphCompilerException(name() + "does not resolve to a vaild source file");
			}
		}
	}
	
	
	public RecordModel getRecord(String name) {
		XformEntry entry = getInternal(name,TopLevel.RECORD);
		return entry != null ? (RecordModel)entry.getModel() : null;
	}


	public ComponentModel getComponent(String name) {
		XformEntry entry = getInternal(name,TopLevel.COMPONENT);
		return entry != null ? (ComponentModel)entry.getModel() : null;
	}

	public TransformationModel getTransformation(String name) {
		XformEntry entry = getInternal(name,TopLevel.TRANSFORMATION);
		return entry != null ? (TransformationModel)entry.getModel() : null;
	}


	private XformEntry getInternal(String name, TopLevel type) {
		XformEntry entry = env.getEntry(name() + "." + name);
		if (entry == null) {
			if (!isStar()) {
				// check if requested name matches with our single import
				if (!getSimpleName().equals(name)) {
					return null;
				}
			} else {
				// if multi-import check if it's in our contents
				if (!contents.contains(name)) {
					return null;
				}
			}
			
			// the record is in contents of this import
			ParserWrapper parser = ParserWrapper.getInstance();
			String fileName = name + DefaultsAndConstants.GRAPH_FILE_SUFFIX;
			entry = parser.parse(new File(importRoot,fileName),type);
			if (!entry.isInError()) {
				ModelBuilder mb = new ModelBuilder(entry);
				mb.buildModel();
			}
		}
		
		return entry;
	}
	
}
