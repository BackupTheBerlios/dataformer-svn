package cz.dataformer.compiler.model;

import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;

public class ComponentModel extends ModelNode 
implements NamedModelNode {
	
	private List<TypeParamModel> ioParams;
	private List<PortModel> inputPorts = new LinkedList<PortModel>();
	private List<PortModel> outputPorts = new LinkedList<PortModel>();
	//private List<MethodModel> methods;

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

	public void setIOParams(List<TypeParamModel> ioParams) {
		this.ioParams = ioParams;
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
	
}