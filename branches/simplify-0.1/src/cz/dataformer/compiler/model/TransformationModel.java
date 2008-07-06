package cz.dataformer.compiler.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import cz.dataformer.ast.Transformation;

public class TransformationModel extends ModelNode implements NamedModelNode {

	private String name;
	private PackageModel pkg;
	private Map<String,ImportModel> imports = new HashMap<String,ImportModel>();
	private Map<String,TransformationModel> depends = new HashMap<String,TransformationModel>();
	private Map<String,ComponentModel> components = new HashMap<String,ComponentModel>();
	private Map<String,RecordModel> records = new HashMap<String,RecordModel>();
	
	
	public TransformationModel(Transformation ast) {
		super(ast,null);
		this.name = ast.name;
	}
	
	public void addDependency(TransformationModel trans) {
		depends.put(trans.getPackage().name() + "." +  trans.name(),trans);
	}
	
	public String name() {
		return name;
	}
	
	public TransformationModel getDependency(String name) {
		return depends.get(name);
	}
	
	public void addComponent(ComponentModel component) {
		components.put(component.name(), component);
	}
	
	public void setPackage(PackageModel pkg) {
		this.pkg = pkg;
	}
	
	public PackageModel getPackage() {
		return pkg;
	}
	
	public void addImport(ImportModel imp) {
		imports.put(imp.name(),imp);
	}
	
	public ImportModel getImport(String fqdn) {
		return imports.get(fqdn);
	}
	
	public LinkedList<ImportModel> getImports() {
		return new LinkedList<ImportModel>(imports.values());
	}
	
	public void addRecord(RecordModel rec) {
		records.put(rec.name(), rec);
	}

	public RecordModel getRecord(String name) {
		return records.get(name);
	}

	public ComponentModel getComponent(String name) {
		return components.get(name);
	}
	
}
