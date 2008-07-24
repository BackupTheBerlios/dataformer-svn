package cz.dataformer.compiler.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;

public class ComponentModel extends TopLevelModel implements NamedModelNode {
	
	private List<IOParamModel> ioParams = new LinkedList<IOParamModel>();
	private List<PortModel> inputPorts = new LinkedList<PortModel>();
	private List<PortModel> outputPorts = new LinkedList<PortModel>();
	private HashMap<String,MethodModel> methods = new HashMap<String,MethodModel>();

	public ComponentModel(ComponentDeclaration ast, TransformationModel owner) {
		super(ast, owner);
		records = new HashMap<String, ImportModel>();
		components = new HashMap<String, ImportModel>();
	}
	
	public void addPort(PortModel port) {
		if (port.isInput()) {
			inputPorts.add(port);
		} else {
			outputPorts.add(port);
		}
	}

	public void addIOParam(IOParamModel ioParam) {
		this.ioParams.add(ioParam);
	}
	
	public IOParamModel getIOParam(String name) {
		for (IOParamModel model : ioParams) {
			if (model.name().equals(name)) {
				return model;
			}
		}
		
		return null;
	}
	
	public List<IOParamModel> getIOParams() {
		return ioParams;
	}

	public int numInputPorts() {
		return inputPorts.size();
	}
	
	public int numOutputPorts() {
		return outputPorts.size();
	}

	public String name() {
		return ((ComponentDeclaration)ast).name;
	}

	public MethodModel getMethod(String name) {
		return methods.get(name);
	}
	
	
	@Override
	public void addTransformationImport(ImportModel imp) {
		throw new IllegalStateException("Component declaration can never import a transformation declaration");
	}
	

	@Override
	public TransformationModel getTransformation(String prefix,String simpleName) {
		throw new IllegalStateException("Component declaration never contains a transformation declaration");
	}
	
	@Override
	public TransformationModel getTransformation(String simpleName) {
		throw new IllegalStateException("Component declaration never contains a transformation declaration");
	}
}