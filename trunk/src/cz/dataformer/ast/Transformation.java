package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.record.RecordDeclaration;

public class Transformation extends DataFormerNode {

	private String name;
	private List<ComponentDeclaration> components;
	private List<RecordDeclaration> records;

	public Transformation(int line, int column, String name, List<ComponentDeclaration> components, List<RecordDeclaration> records) {
		super(line, column);
		this.name = name;
		this.components = components;
		this.records = records;
	}
	
	public List<ComponentDeclaration> getComponents() {
		return components;
	}
	
	public List<RecordDeclaration> getRecords() {
		return records;
	}

}
