package cz.dataformer.compiler;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.ast.expression.FieldAccessExpression;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.statement.ConnectStatement;
import cz.dataformer.compiler.symbol.ComponentSymbol;
import cz.dataformer.compiler.symbol.RecordSymbol;
import cz.dataformer.compiler.symbol.Symbol;
import cz.dataformer.compiler.symbol.SymbolFlags;
import cz.dataformer.compiler.symbol.SymbolTable;
import cz.dataformer.compiler.symbol.TypeSymbol;
import cz.dataformer.compiler.symbol.VariableSymbol;

public class TypeVerifier extends NodeVisitorImpl {

	private ProblemReporter pr = ProblemReporter.getInstance();
	private SymbolTable currentScope;
	private Symbol currentSymbol;
	private DataFormerNode context;
	
	public void verify(XformEntry entry) {

		if (entry.isInError()) {
			return;
		}
		
		currentScope = entry.getSymbol().getScope();
		currentSymbol = entry.getSymbol();
		entry.getAst().accept(this);
	}
	
	@Override
	public void visit(FieldAccessExpression n) {
		if (n.resolvedType != null) {
			return;
		}
		
		super.visit(n.scope);
		TypeSymbol ownerType = n.scope.resolvedType;
		
		if (ownerType == TypeSymbol.ERROR) {
			n.resolvedType = TypeSymbol.ERROR;
		}

		if (! (ownerType instanceof ComponentSymbol) 
			|| ! (ownerType instanceof RecordSymbol)) {
			pr.fieldCannotBeResolved(n);
			n.resolvedType = TypeSymbol.ERROR;
		}
		
		assert ownerType.getScope() != null : "Symbol has null scope";
		// need to do context-sensitive lookup of the symbo, but seems the structures are not OK for this
		// XXX: point of last edit
		VariableSymbol vs = (VariableSymbol)ownerType.getScope().lookup(
				n.field,SymbolFlags.PORT);
//		if (vs == null || vs.getOwner() != n.symbol) {
//			pr.fieldCannotBeResolved(n);
//			return TypeSymbol.ERROR;
//		}
//		
//		n.symbol = vs;
//		return vs.getType();
		
	}
	
	private TypeSymbol verifyType(Expression scope) {
		System.out.println("Expression hit");
		return TypeSymbol.ERROR;
	}
	
	@Override
	public void visit(NameExpression exp) {
		if (exp.resolvedType != null) {
			return;
		}
		
		//	FIXME handle package & imports
		if (context instanceof Transformation) {
			// we are in graph{} block which only contains connections or property initialization
			VariableSymbol vs = (VariableSymbol)currentScope.lookup(exp.name, SymbolFlags.VARIABLE);
			exp.resolvedType = vs.getType();
		} else if (context instanceof MethodDeclaration) {
			// check simple names - variable, field, port or property
			SymbolFlags[] checkOrder = new SymbolFlags[] {
					SymbolFlags.VARIABLE,
					SymbolFlags.FIELD,
					SymbolFlags.PORT,
					SymbolFlags.PROPERTY
			};
			for (SymbolFlags type : checkOrder) {
				VariableSymbol vs = (VariableSymbol)currentScope.lookup(exp.name,type);
				if (vs != null) {
					exp.resolvedType = vs.getType();
				}
			}
		}
	}
	
	public void visit(ConnectStatement n) {
		context = n;
		
		if (! (n.sourcePort instanceof FieldAccessExpression)) {
			pr.expressionNotPortReference(n.sourcePort);
			return;
		}
		
		if (! (n.destPort instanceof FieldAccessExpression)) {
			pr.expressionNotPortReference(n.destPort);
			return;
		}
		
		super.visit(n);
		
		// source port is SOURCE of assignment -> LHS :)
		TypeSymbol rhs = ((FieldAccessExpression)n.sourcePort).resolvedType;
		TypeSymbol lhs =  ((FieldAccessExpression)n.destPort).resolvedType;
		
		if (! isAssignable(lhs,rhs)) {
			pr.cannotConvertType(n.line,n.column,rhs,lhs);
		}
		
	}

	private boolean isAssignable(TypeSymbol lhs, TypeSymbol rhs) {
		// TODO Auto-generated method stub
		return false;
	}

}
