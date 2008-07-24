package cz.dataformer.compiler.model;

import java.util.LinkedList;
import java.util.Map;

import cz.dataformer.DataFormerNode;

public class TopLevelModel extends ModelNode {

	public enum TopLevel {
		RECORD, COMPONENT, TRANSFORMATION;
	}
	
	protected String pkg;
	
	protected Map<String,ImportModel> records;
	protected Map<String,ImportModel> components;
	protected Map<String,ImportModel> transformations;
	
	
	
	public TopLevelModel(DataFormerNode ast, ModelNode owner) {
		super(ast, owner);
	}
	
	public void setPackage(String pkg) {
		this.pkg = pkg;
	}
	
	public String getPackage() {
		return pkg;
	}


	
	public void addRecordImport(ImportModel imp) {
		records.put(imp.name(),imp);
	}
	
	public void addComponentImport(ImportModel imp) {
		components.put(imp.name(),imp);
	}
	
	public void addTransformationImport(ImportModel imp) {
		transformations.put(imp.name(),imp);
	}
	
	public LinkedList<ImportModel> getImports() {
		LinkedList<ImportModel> ret = new LinkedList<ImportModel>();
		ret.addAll(records.values());
		ret.addAll(components.values());
		ret.addAll(transformations.values());
		return ret;
	}
	
	public RecordModel getRecord(String prefix, String simpleName) {
		prefix = prefix == null ? getPackage() : prefix;
		ImportModel imp = records.get(prefix);
		return imp == null ? null : imp.getRecord(simpleName);
	}

	public RecordModel getRecord(String simpleName) {
		for (ImportModel imp : records.values()) {
			RecordModel rec = imp.getRecord(simpleName);
			if (rec != null) {
				return rec;
			}
		}
		
		return null;
	}
	
	public ComponentModel getComponent(String prefix, String simpleName) {
		prefix = prefix == null ? getPackage() : prefix;
		ImportModel imp = components.get(prefix);
		return imp == null ? null : imp.getComponent(simpleName); 
	}
	
	public ComponentModel getComponent(String simpleName) {
		for (ImportModel imp : components.values()) {
			ComponentModel comp = imp.getComponent(simpleName);
			if (comp != null) {
				return comp;
			}
		}
		
		return null;
	}

	
	public TransformationModel getTransformation(String prefix, String simpleName) {
		prefix = prefix == null ? getPackage() : prefix;
		ImportModel imp = transformations.get(prefix);
		return imp == null ? null : imp.getTransformation(simpleName);
	}
	
	public TransformationModel getTransformation(String simpleName) {
		for (ImportModel imp : transformations.values()) {
			TransformationModel trans= imp.getTransformation(simpleName);
			if (trans != null) {
				return trans;
			}
		}
		
		return null;
	}

	
	
	public ImportModel getImport(String fqn) {
		ImportModel ret = records.get(fqn);
		if (ret == null) {
			ret = components.get(fqn);
		}
		if (ret == null) {
			ret = transformations.get(fqn);
		}

		return ret;
	}
}
