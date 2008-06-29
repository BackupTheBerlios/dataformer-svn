package cz.dataformer.compiler;

import java.util.EnumSet;
import java.util.LinkedList;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.TransformationFieldDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.body.ComponentFieldDeclaration;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.record.DelimitedFieldDeclaration;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.FixedFieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.ClassOrInterfaceType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.ast.type.Type;
import cz.dataformer.ast.type.VoidType;
import cz.dataformer.compiler.symbol.ComponentSymbol;
import cz.dataformer.compiler.symbol.DuplicateDeclarationException;
import cz.dataformer.compiler.symbol.JavaLangSymbol;
import cz.dataformer.compiler.symbol.MethodSymbol;
import cz.dataformer.compiler.symbol.RecordSymbol;
import cz.dataformer.compiler.symbol.Symbol;
import cz.dataformer.compiler.symbol.SymbolFlags;
import cz.dataformer.compiler.symbol.SymbolTable;
import cz.dataformer.compiler.symbol.TypeSymbol;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class ASTAnnotator {

	private ProblemReporter pr = ProblemReporter.getInstance();
	
	/**
	 * List of topologic roots i.e. components that have no input
	 * Algo: 
	 * Every component is put at the end of the list and removed when an input
	 * port is found during the pass over AST.
	 */
	private LinkedList<ComponentSymbol> topologicRoots = new LinkedList<ComponentSymbol>();

	public void resolveSymbols(XformEntry entry) {
		// do not touch errorneous entries, since they are empty anyway
		if (entry.isInError()) {
			return;
		}
		
		// resolve imported symbols - causes parsing and processing of other transformations
		ImportResolver ir = new ImportResolver();
		ir.resolveSymbols(entry);
		
		// resolve symbols defined in the current transformation
		DataRecordCollector lr = new DataRecordCollector();
		lr.resolveSymbols(entry);
		
	}

	private class ImportResolver extends NodeVisitorImpl {
		private SymbolTable symTab;
		
		private void addImplicitImports() {
			try {
				symTab.put(new TypeSymbol(null,"java.lang.Boolean",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Char",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Byte",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Short",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Integer",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Long",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Float",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
				symTab.put(new TypeSymbol(null,"java.lang.Double",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC)));
			
				symTab.put(new TypeSymbol(null,"boolean",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"char",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"byte",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"short",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"int",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"long",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"float",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				symTab.put(new TypeSymbol(null,"double",JavaLangSymbol.INSTANCE,EnumSet.of(SymbolFlags.PUBLIC,SymbolFlags.PRIMITIVE)));
				
				symTab.put(TypeSymbol.VOID);
				
			} catch (DuplicateDeclarationException e) {
				// can not happen
				assert false : "Unreachable coede";
			}
		}
		
		public void resolveSymbols(XformEntry entry) {
			this.symTab = entry.getSymbol().getScope();
			
			addImplicitImports();
			// checkCircularImports()
		}
		
	}
	
	
	/**
	 * Data record type collector.
	 * Defines data record types from primitive types.
	 *  
	 * @author mtomcany
	 *
	 */
	private class DataRecordCollector extends NodeVisitorImpl {
		private SymbolTable currentScope;
		private Symbol currentSymbol;

		private boolean hasPorts;
		
		public void resolveSymbols(XformEntry entry) {
			currentSymbol = entry.getSymbol();
			currentScope = entry.getSymbol().getScope();
			entry.getAst().accept(this);
		}
		
		
		public void visit(RecordDeclaration rec) {
			Symbol prevSymbol = currentSymbol;
			
			RecordSymbol recordSymbol = new RecordSymbol(
					rec,rec.name,currentSymbol,
					rec.modifiers.asSymbolFlags());
			
			try {
				rec.symbol = recordSymbol;
				currentScope.put(recordSymbol);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
				return;
			}

			currentSymbol = recordSymbol; 
			currentScope = recordSymbol.getScope();
			
			super.visit(rec);
			
			currentSymbol = prevSymbol;
			currentScope = prevSymbol.getScope();
		}
		
		
		public void visit(DelimitedFieldDeclaration f) {
			// define type of the field first
			super.visit(f);
			
			processField(f);
		}
		
		public void visit(FixedFieldDeclaration f) {
			// define type of the field first
			super.visit(f);

			processField(f);
		}
		
		private void processField(FieldDeclaration f) {
			TypeSymbol typeSym = null;
			// TODO add String handling
			if (f.type instanceof PrimitiveType) {
				typeSym = (TypeSymbol)currentScope.lookup(((PrimitiveType)f.type).type.getName(), SymbolFlags.TYPE);
				assert typeSym != null : "Primitive type was not resolved!";
			} else {
				typeSym = TypeSymbol.ERROR;
			}
			
			VariableSymbol s = new VariableSymbol(f,f.name,SymbolFlags.FIELD, currentSymbol,typeSym,EnumSet.of(SymbolFlags.PUBLIC));
			
			try {
				f.symbol = s;
				currentScope.put(s);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
		}

		
		public void visit(ComponentDeclaration n) {
			
			// put component into the current scope
			ComponentSymbol cs = new ComponentSymbol(n,currentSymbol,n.modifiers.asSymbolFlags());
			
			try {
				n.symbol = cs;
				currentScope.put(cs);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
			
			// create and open component's scope
			Symbol prevSymbol = currentSymbol;
			currentSymbol = cs;
			currentScope = cs.getScope();
			
			/*
			 *  Put the component on topo root stack. 
			 *  Will be possibly removed when checking ports (see visit(Port))
			 */
			topologicRoots.addLast(cs);
		
			// initialize check for ports
			hasPorts = false;
			
			// component's elements processing
			super.visit(n);
			
			// report a component without ports
			if (!hasPorts ) {
				pr.componentHasNoPorts(n);
			}
			
			currentSymbol = prevSymbol;
			currentScope = prevSymbol.getScope();
		}

		public void visit(IOTypeParameter n) {
			// mark the type as generic for later substitution
			TypeSymbol s = new TypeSymbol(
					n,n.name,currentSymbol,
					EnumSet.of(SymbolFlags.GENERIC));
			
			
			try {
				n.symbol = s;
				currentScope.put(s);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
			
		}
		
		/**
		 * IO types in component declaration (header) must be processed
		 * before any of ports is being processed, otherwise type is not resolved
		 * 
		 * Checks if  a component is a topologic root (it has no inputs)
		 */
		public void visit(Port n) {
			TypeSymbol type = (TypeSymbol)currentScope.lookup(n.ioType, SymbolFlags.TYPE, SymbolFlags.GENERIC);
			if (type == null) {
				pr.ioTypeCannotBeResolved(n);
				type = TypeSymbol.ERROR;
			} else {			
				// port type must be one of component's declared I/O parameters 
				if (type.getOwner() != currentSymbol) {
					pr.typeDoesNotMatchIOParams(n);
				}
			}
			
			VariableSymbol vs = new VariableSymbol(n,n.name,SymbolFlags.PORT,currentSymbol,type,n.modifiers.asSymbolFlags());
			/*
			 *  Flag as generic for later type substitution process.
			 *  Ports are automatically generic as they reference component's (generic) IO params
			 */
			vs.flag(SymbolFlags.GENERIC);
			
			try {
				n.symbol = vs;
				currentScope.put(vs);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());			}
			
			// set ports flag 
			hasPorts = true;
			
			// topologic root ? if has an input port -> NO!	 
			if (vs.isAll(SymbolFlags.INPUT) && currentSymbol == topologicRoots.getLast()) {
				topologicRoots.removeLast();
			}

		}

		/**
		 * Records must be processed before processing the component fields
		 */
		public void visit(ComponentFieldDeclaration n) {
			TypeSymbol typeSymbol = resolveType(n.type);
			if (typeSymbol == null) {
				typeSymbol = TypeSymbol.ERROR;
			}
			
			n.type.symbol = typeSymbol;
			VariableSymbol vs = null;
			try {
				vs = new VariableSymbol(n,n.variable.id,SymbolFlags.FIELD,currentSymbol,typeSymbol,n.modifiers.asSymbolFlags());
				// propagate the generic flag to symbol if type is generic
				if (typeSymbol.isAll(SymbolFlags.GENERIC)) {
					vs.flag(SymbolFlags.GENERIC);
				}
				n.symbol = vs;
				currentScope.put(vs);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
		}
		
		@Override
		public void visit(MethodDeclaration m) {
			MethodSymbol ms = new MethodSymbol(m,m.name,currentSymbol,m.modifiers.asSymbolFlags());

			TypeSymbol returnSymbol = null;
			if (m.returnType instanceof VoidType) {
				returnSymbol = TypeSymbol.VOID;
			} else {
				returnSymbol = resolveType(m.returnType);
				if (returnSymbol == null) {
					returnSymbol = TypeSymbol.ERROR;
				}
			}
			m.returnType.symbol = returnSymbol;
			
			/*
			 *  Mark method as generic if the return type is generic.
			 *  @see also parameter's generic check
			 *  
			 */
			if (returnSymbol.isAll(SymbolFlags.GENERIC)) {
				ms.flag(SymbolFlags.GENERIC);
			}
			ms.setReturnSymbol(returnSymbol);
			
			try {
				currentScope.put(ms);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
			
			Symbol prevSymbol = currentSymbol;
			currentSymbol = ms;
			
			// process method body
			super.visit(m);
			
			currentSymbol = prevSymbol;
			currentScope = prevSymbol.getScope();


			// Compute method signature
			LinkedList<TypeSymbol> paramTypes = new LinkedList<TypeSymbol>();

			// parameters are already resolved during visit() call
			for (Parameter param : m.parameters) {
				paramTypes.add(param.type.symbol);
				// mark method as generic if any of it's parameters is generic
				if (param.type.symbol.isAll(SymbolFlags.GENERIC)) {
					ms.flag(SymbolFlags.GENERIC);
				}
			}
			
			ms.setSignature(paramTypes);

		}

		
		public void visit(Parameter n) {
			TypeSymbol typeSymbol = resolveType(n.type);
			if (typeSymbol == null) {
				typeSymbol = TypeSymbol.ERROR;
			}
			
			n.type.symbol = typeSymbol;
			VariableSymbol vs = new VariableSymbol(n,n.id,SymbolFlags.VARIABLE,currentSymbol,typeSymbol,EnumSet.noneOf(SymbolFlags.class));
			if (typeSymbol.isAll(SymbolFlags.GENERIC)) {
				vs.flag(SymbolFlags.GENERIC);
			}
			
			try {
				currentScope.put(typeSymbol);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
		}
		
		/**
		 * Components variables must be handled after all records are handled
		 */
		@Override
		public void visit(TransformationFieldDeclaration c) {
			ComponentSymbol generic = (ComponentSymbol)currentScope.lookup(c.type.name,SymbolFlags.COMPONENT);
			if (generic == null) {
				pr.typeCannotBeResolved(c,c.type.name);
				// FIXME: handle as an error type here and modify instantiate() to do nothing???
				return;
			}

			// check count of types and type validity before going for instantiation
			ComponentDeclaration genericAST = (ComponentDeclaration)generic.getAst();
			if (c.ioParams.size() != genericAST.ioParams.size()) {
				pr.incorrectNumberOfIOParams(c.line,c.column,genericAST);
			}
			
			/*
			 * check that all substitues resolved  to record-types (and not primitive types)
			 */
			for (NameExpression t : c.ioParams) {
				t.symbol = (RecordSymbol)currentScope.lookup(t.name,SymbolFlags.RECORD);
				if (t.symbol == null) {
					t.symbol = TypeSymbol.ERROR;
				}
			}
			
			
			VariableSymbol vs = null;
			try {
				ComponentSymbol specific = generic.instantiate(c.ioParams);
				vs = new VariableSymbol(c,c.name,SymbolFlags.VARIABLE,currentSymbol,specific,c.modifiers.asSymbolFlags());
				c.symbol = vs;
				currentScope.put(vs);
			} catch (DuplicateDeclarationException e) {
				pr.duplicateDeclaration(e.getMessage(),e.getSymbol().getAst());
			}
		}
		
		private TypeSymbol resolveType(Type t) {
			TypeSymbol returnSymbol = null;
			String name = null;
			if (t instanceof PrimitiveType) {
				name = ((PrimitiveType)t).type.getName();
				returnSymbol = (TypeSymbol)currentScope.lookup(name,SymbolFlags.TYPE,SymbolFlags.PRIMITIVE);
				assert returnSymbol != null : "Primitive type was not resolved!";
			} else if (t instanceof ClassOrInterfaceType) {
				name = ((ClassOrInterfaceType)t).name;
				returnSymbol = (TypeSymbol)currentScope.lookup(
						name,SymbolFlags.TYPE,
						SymbolFlags.RECORD);
				if (returnSymbol == null) {
					// no match -> try to match with component's IO types
					returnSymbol = (TypeSymbol)currentScope.lookup(
							name,SymbolFlags.TYPE,
							SymbolFlags.GENERIC);
					if (returnSymbol == null || returnSymbol.getOwner() != currentSymbol) {
						pr.typeCannotBeResolved(t,name);	
						returnSymbol = TypeSymbol.ERROR;
					}
				}
			}
			
			return returnSymbol;
			
		}
		
	}
	
}
