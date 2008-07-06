package cz.dataformer.compiler.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;

public class ComponentModel extends ModelNode 
implements NamedModelNode {
	
	private List<TypeParamModel> ioParams = new LinkedList<TypeParamModel>();
	private List<PortModel> inputPorts = new LinkedList<PortModel>();
	private List<PortModel> outputPorts = new LinkedList<PortModel>();
	private HashMap<String,MethodModel> methods = new HashMap<String,MethodModel>();

	public ComponentModel(ComponentDeclaration ast, TransformationModel owner) {
		super(ast, owner);
	}
	
	public void addPort(PortModel port) {
		if (port.isInput()) {
			inputPorts.add(port);
		} else {
			outputPorts.add(port);
		}
	}

	public void addIOParam(TypeParamModel ioParam) {
		this.ioParams.add(ioParam);
	}
	
	public TypeParamModel getIOParam(String name) {
		for (TypeParamModel model : ioParams) {
			if (model.name().equals(name)) {
				return model;
			}
		}
		
		return null;
	}
	
	public List<TypeParamModel> getIOParams() {
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
	
}