package cz.dataformer.compiler.model;

import java.util.LinkedList;
import java.util.List;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.ComponentFieldDeclaration;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.CompilerEnvironment;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.Utilities;
import cz.dataformer.compiler.XformEntry;


public class ModelBuilder extends NodeVisitorImpl {
	
	private static final ModelBuilder INSTANCE = new ModelBuilder();
	private ProblemReporter pr = ProblemReporter.getInstance();
	private List<ComponentModel> topoRoots = new LinkedList<ComponentModel>();
	
	
	private XformEntry current;
	private ModelNode owner;
	private CompilerEnvironment env;
	private List<ModelNode> pendingExtends = new LinkedList<ModelNode>();
	
	public static ModelBuilder getInstance() {
		return INSTANCE;
	}

	
	public void buildModel(XformEntry entry) {
		current = entry;
		entry.getAst().accept(this);
		
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
		String fqdn = Utilities.nameToString(ast.name);
		if (t.getImport(fqdn) != null) {
			pr.duplicateDeclaration("Duplicate import declaration",ast);
			return;
		}
		
		ImportModel im = new ImportModel(ast,owner);
		im.setStarImport(ast.isAsterisk);
		t.addImport(im);
		
	}
	
	public void visit(RecordDeclaration ast) {
		RecordModel rec = new RecordModel(ast,(TransformationModel)this.owner);
		ModelNode prevOwner = this.owner;
		
		this.owner = rec;
		super.visit(ast);
		owner = prevOwner;
		
		if (! rec.hasFields()) {
			pr.recordHasNoFields(ast);
		}
		
		if (ast.extend != null) {
			pendingExtends.add(rec);
		}
		
	}
	
	
	public void visit(FieldDeclaration ast) {
		
		if (ast.type instanceof PrimitiveType == false) {
			pr.fieldTypeCannotBeComposite(ast);
			return;
		}
		
		RecordModel rec = (RecordModel)owner;
		FieldModel field = new FieldModel(ast,rec);
		rec.addField(field);
		
	}
	
	public void visit(ComponentDeclaration ast) {
		TransformationModel owner = (TransformationModel)this.owner;
		ComponentModel comp = new ComponentModel(ast,owner);
		
		
		LinkedList<String> ioParams = new LinkedList<String>();
		for (IOTypeParameter p : ast.ioParams) {
			ioParams.add(p.name);
		}
		comp.setIOParams(ioParams);
		
		if (ast.extend != null) {
			pendingExtends.add(comp); 
		}
	
		TransformationModel prevOwner = owner;

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
		if (! component.getIOParams().contains(ast.ioType)) {
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
