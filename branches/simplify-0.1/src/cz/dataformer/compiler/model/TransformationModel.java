package cz.dataformer.compiler.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.Transformation;

public class TransformationModel extends TopLevelModel implements NamedModelNode {

	private String name;
	private List<ComponentModel> topoRoots = new LinkedList<ComponentModel>();
	
	
	public TransformationModel(Transformation ast) {
		super(ast,null);
		this.name = ast.name;
		records = new HashMap<String,ImportModel>();
		components = new HashMap<String,ImportModel>();
		transformations = new HashMap<String,ImportModel>();
	}
	
	public String name() {
		return name;
	}
	
	
	public LinkedList<ImportModel> getImports() {
		LinkedList<ImportModel> ret = new LinkedList<ImportModel>();
		ret.addAll(records.values());
		ret.addAll(components.values());
		ret.addAll(transformations.values());
		return ret;
	}
	
	public void addTopologicRoot(ComponentModel comp) {
		topoRoots.add(comp);
	}


}
