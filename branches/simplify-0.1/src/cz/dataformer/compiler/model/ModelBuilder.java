package cz.dataformer.compiler.model;

import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.record.DelimitedFieldDeclaration;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.FixedFieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.DataRecordType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.CompilerEnvironment;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.Utilities;
import cz.dataformer.compiler.XformEntry;


public class ModelBuilder extends NodeVisitorImpl {
	
	private static final ModelBuilder INSTANCE = new ModelBuilder();
	private ProblemReporter pr = ProblemReporter.getInstance();
	
	
	private static final class BuilderState {
		private XformEntry entry;
		private ModelNode owner;
		public ModelNode prevOwner;
	}
		public CompilerEnvironment env;
	
	private final BuilderState state = new BuilderState();
	
	
	// records and components with unresolved extends hierarchy
	private List<ModelNode> pendingExtends = new LinkedList<ModelNode>();
	
	public static ModelBuilder getInstance() {
		return INSTANCE;
	}

	
	public void buildModel(XformEntry entry) {
		setEntry(entry);
		getEntry().getAst().accept(this);
		
		// buildExtends()
	}
 	
	
	public void visit(Transformation ast) {
		TransformationModel trans = new TransformationModel(ast);
		getEntry().setModel(trans);
		
		
		PackageModel pkg = new PackageModel(ast.packageName,trans);
		trans.setPackage(pkg);

		pushOwner(trans);
		
		visitNode(ast.imports);
		visitNode(ast.records);
		visitNode(ast.components);
		visitNode(ast.variables);
		visitNode(ast.graph);
		
		popOwner();
	}



	public void visit(ImportDeclaration ast) {
		TransformationModel t = getOwner();
		
		if (t.getImport(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate import declaration",ast);
			return;
		}
		
		ImportModel im = new ImportModel(ast,t);
		t.addImport(im);
		
	}
	
	public void visit(RecordDeclaration ast) {
		TransformationModel t = getOwner();
		
		if (t.getRecord(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate record declaration", ast);
			return;
		}
		
		if (t.getComponent(ast.name) != null) {
			pr.duplicateDeclaration("Component with the same name already declared", ast);
			return;
		}
		
		RecordModel rec = new RecordModel(ast,t);
		
		pushOwner(rec);
		super.visit(ast);
		popOwner();
		
		if (rec.numFields() < 1) {
			pr.recordHasNoFields(ast);
		}
		
		if (ast.extend != null) {
			pendingExtends.add(rec);
		}
		
		t.addRecord(rec);
		
	}
	
	@Override
	public void visit(DelimitedFieldDeclaration f) {
		visitField(f);
	}
	
	@Override
	public void visit(FixedFieldDeclaration f) {
		visitField(f);
	}
	
	private void visitField(FieldDeclaration ast) {
		
		if (ast.type instanceof PrimitiveType == false) {
			pr.fieldTypeCannotBeComposite(ast);
			return;
		}
		
		RecordModel rec = getOwner();
		if (rec.getField(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate field declaration", ast);
			return;
		}
		
		VariableModel field = new VariableModel(ast,rec,Utilities.typeEnumToModel(ast.type));
		rec.addField(field);
		
	}
	
	

	public void visit(ComponentDeclaration ast) {
		TransformationModel t = getOwner();
		if (t.getComponent(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate component declaration", ast);
			return;
		}
		
		if (t.getRecord(ast.name) != null) {
			pr.duplicateDeclaration("Record with the same name already declared",ast);
			return;
		}
		
		ComponentModel comp = new ComponentModel(ast,t);
		
		
		for (IOTypeParameter p : ast.ioParams) {
			IOParamModel model = new IOParamModel(p,comp);
			if (comp.getIOParam(p.name) != null) {
				pr.duplicateDeclaration("Duplicate IO type parameter declaration", p);
			} else {
				comp.addIOParam(model);
			}
		}
		
		
		if (ast.extend != null) {
			pendingExtends.add(comp); 
		}
	
		pushOwner(comp);
		super.visit(ast);
		popOwner();
		
		if (comp.numInputPorts() == 0 ) {
			if (comp.numOutputPorts() == 0) {
				// component must have at least one input or output port
				pr.componentHasNoPorts(ast);
			} else {
				markTopologicRoot(comp);
			}
		}
		
	}
	
	public void visit(Port ast) {
		ComponentModel component = getOwner();
		// check if type parameter referenced by port is among parameters declared by component
		IOParamModel io = component.getIOParam(ast.ioType);
		if (io== null) {
			pr.typeDoesNotMatchIOParams(ast);
			return;
		}
		
		
		PortModel port = new PortModel(ast,component, new IORefModel(ast.ioType));
		component.addPort(port);
	}
	
	
	@Override
	public void visit(MethodDeclaration ast) {
		ComponentModel component = getOwner();
		
		MethodModel meth = new MethodModel(ast,component);
		MethodModel dup = component.getMethod(ast.name);
		if (meth.equals(dup)) {
			pr.duplicateDeclaration("Duplicate method declaration", ast);
			return;
		}
		
		for (Parameter p : ast.parameters) {
			if (meth.getVariable(p.name) != null) {
				pr.duplicateDeclaration("Duplicate formal parameter declaration", p);
			}

			VariableModel var = null;
			if (p.type instanceof PrimitiveType) {
				var = new VariableModel(p,meth, Utilities.typeEnumToModel((PrimitiveType)p.type));
			} else if (p.type instanceof DataRecordType) {
				// this can be either reference to an IO param or a real DataRecordType
				DataRecordType dt = (DataRecordType)p.type;
				if (component.getIOParam(dt.name) != null) {
					// reference to an IO type
					var = new VariableModel(p,meth,new IORefModel(dt.name));
				} else {
					// reference to a data record
					var = new VariableModel(p,meth,new DataRecordTypeModel(dt.name));
				}
			} else {
				// TODO: array!
			}
		}
	}

	private void markTopologicRoot(ComponentModel comp) {
		getEntry().getModel().addTopologicRoot(comp);
	}


	public void setEntry(XformEntry current) {
		state.entry = current;
	}


	public XformEntry getEntry() {
		return state.entry;
	}

	/**
	 * Changes the {@link #owner} while storing it in {@link #prevOwner}
	 * for use in {@link #popOwner()}
	 * 
	 * @param newOwner
	 * @return original value of {@link #owner}
	 */
	private ModelNode pushOwner(ModelNode newOwner) {
		state.prevOwner = getOwner();
		state.owner = newOwner;
		
		return state.prevOwner;
	}

	/**
	 * Sets the {@link #owner} with the value of {@link #prevOwner}
	 * 
	 * @return original value of {@link #owner} 
	 */
	private ModelNode popOwner() {
		ModelNode ret = state.owner;
		state.owner = state.prevOwner;
		
		return ret;
	}


	public void setOwner(ModelNode owner) {
		state.owner = owner;
	}


	public <T extends ModelNode>  T getOwner() {
		return (T) state.owner;
	}

	
	/* Additional syntactic checks */
	// isArrayInitializerUsedWithArrayVariable - java?
	// arrayInitializerHasCorrectNumberOfFields - java?
	// switchHasOnlyOneDefaultEntry - java?
	
	/* Port related checks */
	// isRequiredPortConnected
	// isDuplicateConnection
	
	
	/* Flow related checks */
	// checkCyclicDataFlow
	
	
}
