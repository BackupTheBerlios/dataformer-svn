package cz.dataformer.compiler;

import java.util.HashMap;

import cz.dataformer.compiler.model.ComponentModel;
import cz.dataformer.compiler.model.TransformationModel;

public class CompilerEnvironment {

	private HashMap<String,TransformationModel> transformations = new HashMap<String,TransformationModel>();

	public TransformationModel getTransformation(String fqdName) {
		return transformations.get(fqdName);
	}
	
	public ComponentModel getComponent(String name) {
		//FIXME this should return something if used
		return null;
	}
}
