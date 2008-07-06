package cz.dataformer.compiler.model;

import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.record.DelimitedFieldDeclaration;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.FixedFieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.CompilerEnvironment;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.XformEntry;


public class ModelBuilder extends NodeVisitorImpl {
	
	private static final ModelBuilder INSTANCE = new ModelBuilder();
	private ProblemReporter pr = ProblemReporter.getInstance();
	private List<ComponentModel> topoRoots = new LinkedList<ComponentModel>();
	
	
	private XformEntry current;
	private ModelNode owner;
	private CompilerEnvironment env;
	// records and components with unresolved extends hierarchy
	private List<ModelNode> pendingExtends = new LinkedList<ModelNode>();
	
	public static ModelBuilder getInstance() {
		return INSTANCE;
	}

	
	public void buildModel(XformEntry entry) {
		current = entry;
		current.getAst().accept(this);
		
		// buildExtends()
	}
 	
	
	public void visit(Transformation ast) {
		TransformationModel trans = new TransformationModel(ast);
		
		PackageModel pkg = new PackageModel(ast.packageName,trans);
		trans.setPackage(pkg);

		this.owner = trans;
		
		super.visit(ast);
	}

	public void visit(ImportDeclaration ast) {
		TransformationModel t = (TransformationModel)owner;
		
		if (t.getImport(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate import declaration",ast);
			return;
		}
		
		ImportModel im = new ImportModel(ast,owner);
		t.addImport(im);
		
	}
	
	public void visit(RecordDeclaration ast) {
		TransformationModel t = (TransformationModel)this.owner;
		
		if (t.getRecord(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate record declaration", ast);
			return;
		}
		
		if (t.getComponent(ast.name) != null) {
			pr.duplicateDeclaration("Component with the same name already declared", ast);
			return;
		}
		
		RecordModel rec = new RecordModel(ast,t);
		ModelNode prevOwner = this.owner;
		
		this.owner = rec;
		super.visit(ast);
		this.owner = prevOwner;
		
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
		
		RecordModel rec = (RecordModel)owner;
		if (rec.getField(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate field declaration", ast);
			return;
		}
		
		FieldModel field = new FieldModel(ast,rec);
		rec.addField(field);
		
	}
	
	public void visit(ComponentDeclaration ast) {
		TransformationModel t = (TransformationModel)this.owner;
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
			TypeParamModel model = new TypeParamModel(p,comp);
			if (comp.getIOParam(p.name) != null) {
				pr.duplicateDeclaration("Duplicate IO type parameter declaration", p);
			} else {
				comp.addIOParam(model);
			}
		}
		
		
		if (ast.extend != null) {
			pendingExtends.add(comp); 
		}
	
		TransformationModel prevOwner = t;

		this.owner = comp;
		super.visit(ast);
		this.owner = prevOwner;
		
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
		ComponentModel component = (ComponentModel)this.owner;
		// check if type parameter referenced by port is among parameters declared by component
		if (component.getIOParam(ast.ioType) == null) {
			pr.typeDoesNotMatchIOParams(ast);
			return;
		}
		
		PortModel port = new PortModel(ast,component);
		component.addPort(port);
	}
	

	private void markTopologicRoot(ComponentModel comp) {
		topoRoots.add(comp);
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
