package cz.dataformer.compiler.model;

import cz.dataformer.DataFormerNode;
import cz.dataformer.NamedDataFormerNode;
import cz.dataformer.ast.body.Parameter;

public abstract class VariableModel extends ModelNode implements NamedModelNode {

	private String name;

	private static class IORef extends VariableModel {
		private String ioRef;
		private RecordModel type;
		
		public IORef(DataFormerNode ast, MethodModel owner, String ioRef) {
			super(ast,owner);
			this.ioRef = ioRef;
		}
		
	}
	
	private static class PrimitiveVar extends VariableModel {
		private PrimitiveTypeModel type;
		
		public PrimitiveVar(DataFormerNode ast, ModelNode owner, PrimitiveTypeModel type) {
			super(ast, owner);
			this.type = type;
		}
	}

	
	private static class DataRecordVar extends VariableModel {

		private RecordModel type;

		public DataRecordVar(DataFormerNode ast, ModelNode owner, RecordModel type) {
			super(ast, owner);
			this.type = type;
		}
		
	}
	
	public VariableModel(DataFormerNode ast, ModelNode owner) {
		super(ast, owner);
	}
	
	public String name() {
		return ((NamedDataFormerNode)ast).name;
	}
	
	public boolean isIOReference() {
		return this instanceof IORef;
	}
	
	// instance with type referring to an IO param
	public static VariableModel instance(DataFormerNode ast, MethodModel owner, String ioRef) {
		return new IORef(ast,owner,ioRef);
	}
	
	public static VariableModel instance(DataFormerNode ast, ModelNode owner, PrimitiveTypeModel type) {
		return new PrimitiveVar(ast,owner,type);
	}
	
	public static VariableModel instance(DataFormerNode ast, ModelNode owner, RecordModel type) {
		return new DataRecordVar(ast,owner,type);
	}
	
}
