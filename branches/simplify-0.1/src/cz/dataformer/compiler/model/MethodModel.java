package cz.dataformer.compiler.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.body.MethodDeclaration;

public class MethodModel extends ModelNode implements NamedModelNode {

	private List<VariableModel> formalParams;
	private TypeModel returnType;
	private HashMap<String,VariableModel> localVariables;
	
	public MethodModel(MethodDeclaration ast, ComponentModel owner) {
		super(ast, owner);
	}

	public String name() {
		return ((MethodDeclaration)ast).name;
	}
	
	/**
	 * We store parameters and variables together for fast name-based access
	 * @param param
	 */
	public void addParam(VariableModel param) {
		if (formalParams == null) {
			formalParams = new LinkedList<VariableModel>();
		}
		formalParams.add(param);
		
		if (localVariables == null) {
			localVariables = new HashMap<String, VariableModel>();
		}
		localVariables.put(param.name(), param);
	}
	
	public void addVariable(VariableModel var) {
		if (localVariables == null) {
			localVariables = new HashMap<String, VariableModel>();
		}
		
		localVariables.put(var.name(), var);
	}
	
	public List<VariableModel> getParams() {
		List<VariableModel> ret =  Collections.emptyList();
		return formalParams == null ? ret : formalParams;
	}
	
	public VariableModel getVariable(String name) {
		if (localVariables == null) {
			return null;
		}
		
		return localVariables.get(name);
	}
	
	public TypeModel getReturnType() {
		return returnType; 
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof MethodModel == false) {
			return false;
		}
		
		MethodModel other = (MethodModel)obj;
		
		if (other.name().equals(this.name())) {
			if (other.getParams().equals(this.getParams())) {
				if (other.getReturnType().equals(this.getReturnType())) {
					return true;
				}
			}
		}
		
		return false;
	}

	

}
