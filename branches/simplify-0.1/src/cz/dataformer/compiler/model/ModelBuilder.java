package cz.dataformer.compiler.model;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.record.DelimitedFieldDeclaration;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.FixedFieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.DataRecordType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.CompilerEnvironment;
import cz.dataformer.compiler.FileManager;
import cz.dataformer.compiler.GraphCompilerException;
import cz.dataformer.compiler.ProblemReporter;
import cz.dataformer.compiler.Utilities;
import cz.dataformer.compiler.XformEntry;

public class ModelBuilder extends NodeVisitorImpl {

	private ProblemReporter pr = ProblemReporter.getInstance();

	private XformEntry entry;
	private ModelNode owner;
	private ModelNode prevOwner;

	public CompilerEnvironment env = CompilerEnvironment.getInstance();

	public ModelBuilder(XformEntry entry) {
		this.entry = entry;
	}
	
	
	/**
	 * Builds model for the given pre-parsed entry.
	 * The method gets called recursively to build model for imports.
	 * In such case the builder state should be stored by {@link #pushState()} method
	 * After building the import the state should be restored by {@link #popState()}
	 * @param entry 	parsed entry to build model for
	 */
	public void buildModel() {
		try {
			getEntry().getAst().accept(this);
			env.addEntry(getEntry());
		} catch (GraphCompilerException e) {
			getEntry().setInError();
		}
	}
	
	public void visit(Transformation ast) throws GraphCompilerException {
		TransformationModel trans = new TransformationModel(ast);
		getEntry().setModel(trans);

		trans.setPackage(ast.pkg.name);
		
		// add sub-packages for records and components as default imports
		FileManager fileMgr = FileManager.getInstance();
		String prefix = ast.pkg.name + "." + "record";
		if (fileMgr.getSourcePathEntry(prefix) != null) {
			ImportModel imp = new ImportModel(new ImportDeclaration(-1,-1,prefix),trans);
			imp.populate();
			trans.addRecordImport(imp);
		}

		prefix = ast.pkg.name + "." + "component";
		if (fileMgr.getSourcePathEntry(prefix) != null) {
			ImportModel imp = new ImportModel(new ImportDeclaration(-1,-1,prefix),trans);
			imp.populate();
			trans.addComponentImport(imp);
		}

		pushOwner(trans);

		visitNode(ast.imports);
		visitNode(ast.variables);
		visitNode(ast.graph);

		popOwner();
	}

	
	public void visit(ImportDeclaration ast) throws GraphCompilerException {
		TopLevelModel t = getOwner();

		// handles exact-match duplicates
		if (t.getImport(ast.getFqn()) != null) {
			pr.duplicateDeclaration("Duplicate import declaration", ast);
			return;
		}
		
		// two single-type imports cannot import the same type
		// i.e. have the same simple name
		if (! ast.isStar) {
			for (ImportModel other : t.getImports()) {
				if (! other.isStar()) {
					if (other.getSimpleName().equals(ast.simpleName)) {
						ImportDeclaration id = other.getAst();
						pr.collidingSingleImport(ast,id);
					}
				}
			}
		}

		ImportModel imp = null;
		// check for illegal imports and add valid ones
		if (ast.prefix.contains(".record.")) {
			imp = new ImportModel(ast,t);
			imp.populate();
			t.addRecordImport(imp);
		} else if (ast.prefix.contains(".component.")) {
			if (t instanceof RecordModel) {
				pr.recordCannotImport(ast,"component");
			} else {
				imp = new ImportModel(ast,t);
				imp.populate();
				t.addComponentImport(imp);
			}
		} else {
			if (t instanceof TransformationModel) {
				imp = new ImportModel(ast,t);
				imp.populate();
				t.addTransformationImport(imp);
			} else if (t instanceof RecordModel) {
				pr.recordCannotImport(ast, "transformation");
			} else {
				pr.componentCannotImportTransformation(ast);
			}
		}
		
	}

	public void visit(RecordDeclaration ast) throws GraphCompilerException {
		RecordModel rec = new RecordModel(ast);
		getEntry().setModel(rec);

		rec.setPackage(ast.pkg.name);
		
		// we need to resolve imports first in case we have extensions
		visitNode(ast.imports);

		
		// check and resolve extension
		if (ast.extend != null) {
			for (NameExpression nameExp : ast.extend) {
				RecordModel ext = null;
				if (nameExp.isQualified()) {
					if (rec.getImport(nameExp.prefix) == null) {
						// we don't have this import yet: create an artificial one
						ImportModel imp = new ImportModel(new ImportDeclaration(-1,-1,nameExp.prefix,nameExp.name),rec);
						imp.populate();
						rec.addRecordImport(imp);
					}
					// there is a fqdn available: use it to quickly identify the import
					ext = rec.getRecord(nameExp.prefix,nameExp.name);
				} else {
					// only simple name: conduct serial search in imports
					ext = rec.getRecord(ast.name);
				}
				
				if (ext == null) {
					pr.recordCannotBeResolved(nameExp);
				}
			}
		}

		pushOwner(rec);
		visitNode(ast.fields);
		popOwner();

		if (rec.numFields() < 1) {
			pr.recordHasNoFields(ast);
		}

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

		VariableModel field = VariableModel.instance(ast, rec, Utilities.typeEnumToModel(ast.type));
		rec.addField(field);

	}

	public void visit(ComponentDeclaration ast) throws GraphCompilerException {
		TransformationModel t = getOwner();
		if (t.getComponent(ast.name) != null) {
			pr.duplicateDeclaration("Duplicate component declaration", ast);
			return;
		}

		if (t.getRecord(ast.name) != null) {
			pr.duplicateDeclaration(
					"Record with the same name already declared", ast);
			return;
		}

		ComponentModel comp = new ComponentModel(ast, t);

		for (IOTypeParameter p : ast.ioParams) {
			IOParamModel model = new IOParamModel(p, comp);
			if (comp.getIOParam(p.name) != null) {
				pr.duplicateDeclaration(
						"Duplicate IO type parameter declaration", p);
			} else {
				comp.addIOParam(model);
			}
		}

//		if (ast.extend != null) {
//			pendingExtends.add(comp);
//		}

		pushOwner(comp);
		super.visit(ast);
		popOwner();

		if (comp.numInputPorts() == 0) {
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
		// check if type parameter referenced by port is among parameters
		// declared by component
		IOParamModel io = component.getIOParam(ast.ioType);
		if (io == null) {
			pr.typeDoesNotMatchIOParams(ast);
			return;
		}

		PortModel port = new PortModel(ast, component, new IORefModel(
				ast.ioType));
		component.addPort(port);
	}

	@Override
	public void visit(MethodDeclaration ast) {
		ComponentModel component = getOwner();

		MethodModel meth = new MethodModel(ast, component);
		MethodModel dup = component.getMethod(ast.name);
		if (meth.equals(dup)) {
			pr.duplicateDeclaration("Duplicate method declaration", ast);
			return;
		}

		for (Parameter p : ast.parameters) {
			if (meth.getVariable(p.name) != null) {
				pr.duplicateDeclaration(
						"Duplicate formal parameter declaration", p);
			}

			VariableModel var = null;
			if (p.type instanceof PrimitiveType) {
				var = VariableModel.instance(p, meth, Utilities.typeEnumToModel((PrimitiveType) p.type));
			} else if (p.type instanceof DataRecordType) {
				// this can be either reference to an IO param or a DataRecordType
				DataRecordType dt = (DataRecordType) p.type;
				
				if (!dt.name.isQualified()) {
					// canonical name can be param/local data record
					if (component.getIOParam(dt.name.name) != null) {
						// reference to an IO type
						var = VariableModel.instance(p, meth, dt.name.name);
					} 
				} 
				
				if (var == null) {
					// still not resolved - try to resolve to a data record
					// no need to pushState() since types are already resolved
					TransformationModel tm = getEntry().getModel();
					RecordModel rec = tm.getRecord(dt.name.name);
					if (rec == null) {
						pr.recordCannotBeResolved(dt.name);
					}
					var = VariableModel.instance(p,meth,rec);
				}
			} else {
				// TODO: array!
			}
		}
	}

	private void markTopologicRoot(ComponentModel comp) {
		//getEntry().getModel().addTopologicRoot(comp);
	}

	public void setEntry(XformEntry current) {
		entry = current;
	}

	public XformEntry getEntry() {
		return entry;
	}

	/**
	 * Changes the {@link #owner} while storing it in {@link #prevOwner} for use
	 * in {@link #popOwner()}
	 * 
	 * @param newOwner
	 * @return original value of {@link #owner}
	 */
	private ModelNode pushOwner(ModelNode newOwner) {
		prevOwner = getOwner();
		owner = newOwner;

		return prevOwner;
	}

	/**
	 * Sets the {@link #owner} with the value of {@link #prevOwner}
	 * 
	 * @return original value of {@link #owner}
	 */
	private ModelNode popOwner() {
		ModelNode ret = owner;
		owner = prevOwner;

		return ret;
	}

	public void setOwner(ModelNode owner) {
		this.owner = owner;
	}

	@SuppressWarnings("unchecked")
	public <T extends ModelNode> T getOwner() {
		return (T) owner;
	}

//	private void pushState() {
//		stateStack.addFirst(state);
//	}
//
//	private void popState() {
//		this.state = stateStack.removeFirst();
//	}

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
