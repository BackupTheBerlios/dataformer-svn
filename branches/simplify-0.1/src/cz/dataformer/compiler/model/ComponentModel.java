package cz.dataformer.compiler.model;

import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;

public class ComponentModel extends NamedModelNode {
	
	private List<String> ioParams;
	private List<PortModel> inputPorts;
	private List<PortModel> outputPorts;
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

	public void setIOParams(List<String> ioParams) {
		this.ioParams = ioParams;
	}
	
	public List<String> getIOParams() {
		return ioParams;
	}

	public int numInputPorts() {
		return inputPorts.size();
	}
	
	public int numOutputPorts() {
		return outputPorts.size();
	}
	
}