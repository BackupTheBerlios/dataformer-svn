package cz.dataformer.ast;

import java.util.List;
import java.util.ListIterator;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.ComponentFieldDeclaration;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.body.Modifiers;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.body.VariableDeclarator;
import cz.dataformer.ast.expression.ArrayAccessExpression;
import cz.dataformer.ast.expression.ArrayAllocationExpression;
import cz.dataformer.ast.expression.ArrayInitializerExpression;
import cz.dataformer.ast.expression.AssignmentExpression;
import cz.dataformer.ast.expression.BinaryExpression;
import cz.dataformer.ast.expression.BooleanLiteralExpression;
import cz.dataformer.ast.expression.ConditionalExpression;
import cz.dataformer.ast.expression.FieldAccessExpression;
import cz.dataformer.ast.expression.IntegerLiteralExpression;
import cz.dataformer.ast.expression.IntegerLiteralMinValueExpression;
import cz.dataformer.ast.expression.LongLiteralExpression;
import cz.dataformer.ast.expression.LongLiteralMinValueExpression;
import cz.dataformer.ast.expression.MethodCallExpression;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.expression.QualifiedNameExpression;
import cz.dataformer.ast.expression.StreamOperationExpression;
import cz.dataformer.ast.expression.StringLiteralExpression;
import cz.dataformer.ast.expression.UnaryExpression;
import cz.dataformer.ast.expression.VariableDeclarationExpression;
import cz.dataformer.ast.record.DelimitedFieldDeclaration;
import cz.dataformer.ast.record.FixedFieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.ast.statement.BreakStatement;
import cz.dataformer.ast.statement.CatchClause;
import cz.dataformer.ast.statement.ConnectStatement;
import cz.dataformer.ast.statement.ContinueStatement;
import cz.dataformer.ast.statement.DoStatement;
import cz.dataformer.ast.statement.EmptyStatement;
import cz.dataformer.ast.statement.ExpressionStatement;
import cz.dataformer.ast.statement.ForStatement;
import cz.dataformer.ast.statement.ForeachStatement;
import cz.dataformer.ast.statement.IfStatement;
import cz.dataformer.ast.statement.ReturnStatement;
import cz.dataformer.ast.statement.SwitchEntryStatement;
import cz.dataformer.ast.statement.SwitchStatement;
import cz.dataformer.ast.statement.ThrowStatement;
import cz.dataformer.ast.statement.TryStatement;
import cz.dataformer.ast.statement.WhileStatement;
import cz.dataformer.ast.type.ClassOrInterfaceType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.ast.type.ReferenceType;
import cz.dataformer.ast.type.VoidType;

public abstract class NodeTranslator implements NodeVisitor {

	/** Result of the last translation */
	protected DataFormerNode transResult = null;
	
	public void visit(DataFormerNode n) {
		assert false;
	}

    /** Visitor method: Translate a single node   */
    @SuppressWarnings("unchecked")
    public <T extends DataFormerNode> T translate(T node) {
        if (node == null) {
            return null;
        } else {
            node.accept(this);
            DataFormerNode result = this.transResult;
            this.transResult= null;
            return (T)result; // XXX cast
        }
    }
	
    /** Visitor method: translate a list of nodes.
     */
    public <T extends DataFormerNode> List<T> translate(List<T> trees) {
        if (trees == null) { 
        	return null;
        }

        ListIterator<T> iter = trees.listIterator();
        T value = null;
		while (iter.hasNext()) {
			value = iter.next();
			iter.set(translate(value));
		}
        
        return trees;
    }
    
    public abstract String translateName(String name);
    
    public abstract Modifiers translateModifiers(Modifiers mods);
    
	
	public void visit(Transformation n) {
		n.packageName = translate(n.packageName);
		n.imports = translate(n.imports);
		n.name = translateName(n.name);
		n.records = translate(n.records);
		n.components = translate(n.components);
		n.variables = translate(n.variables);
		n.graph = translate(n.graph);
		
		this.transResult = n;
		
	}

	public void visit(RecordDeclaration r) {
		r.fields = translate(r.fields);
		
		this.transResult = r;
	}

	public void visit(ComponentDeclaration n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.name = translateName(n.name);
		n.ioParams = translate(n.ioParams);
		n.members = translate(n.members);
		n.main = translate(n.main);
		
		this.transResult = n;
	}

	public void visit(MainBlock n) {
		n.block = translate(n.block);
		
		this.transResult = n;
	}

	public void visit(MethodDeclaration n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.returnType = translate(n.returnType);
		n.name = translateName(n.name);
		n.parameters = translate(n.parameters);
		n.throws_ = translate(n.throws_);
		n.block = translate(n.block);
		
		this.transResult = n;
	}

	public void visit(Parameter n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.type = translate(n.type);
		n.id = translateName(n.id);

		this.transResult = n;
	}

	public void visit(Port n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.name = translateName(n.name);
		
		this.transResult = n;
	}

	public void visit(VariableDeclarator n) {
		n.id = translateName(n.id);
		n.init = translate(n.init);
		
		this.transResult = n;
	}

	public void visit(ClassOrInterfaceType n) {
		n.name = translateName(n.name);
		// we won't visit n.scope, as it would lead us to some other branch
		
		this.transResult = n;
	}

	public void visit(IOTypeParameter n) {
		n.name = translateName(n.name);
		
		this.transResult = n;
	}

	public void visit(PrimitiveType n) {
		this.transResult = n;
	}

	public void visit(ReferenceType n) {
		n.type = translate(n.type);
		
		this.transResult = n;
	}

	public void visit(VoidType n) {
		this.transResult = n;		
	}

	public void visit(ArrayAccessExpression n) {
		n.name = translate(n.name);
		n.index = translate(n.index);
		
		this.transResult = n;
	}

	public void visit(ArrayInitializerExpression n) {
		n.values = translate(n.values);
		
		this.transResult = n;
	}

	public void visit(AssignmentExpression n) {
		n.target = translate(n.target);
		n.value = translate(n.value);
		
		this.transResult = n;
	}

	public void visit(BinaryExpression n) {
		n.left = translate(n.left);
		n.right = translate(n.right);
		
		this.transResult = n;
	}

	public void visit(ConditionalExpression n) {
		n.condition = translate(n.condition);
		n.thenExpr = translate(n.thenExpr);
		n.elseExpr = translate(n.elseExpr);
		
		this.transResult = n;
	}

	public void visit(FieldAccessExpression n) {
		this.transResult = n;
		// we won't visit n.scope  as it would lead us to another branch
	}

	public void visit(StringLiteralExpression n) {
		this.transResult = n;
	}

	public void visit(IntegerLiteralExpression n) {
		this.transResult = n;		
	}

	public void visit(LongLiteralExpression n) {
		this.transResult = n;		
	}

	public void visit(IntegerLiteralMinValueExpression n) {
		this.transResult = n;
	}

	public void visit(LongLiteralMinValueExpression n) {
		this.transResult = n;		
	}

	public void visit(BooleanLiteralExpression n) {
		this.transResult = n;	}

	public void visit(MethodCallExpression n) {
		n.name = translateName(n.name);
		n.args = translate(n.args);
		// we won't visit n.scope as it would lead us to another branch
		this.transResult = n;
	}

	public void visit(NameExpression n) {
		this.transResult = n;
	}

	public void visit(QualifiedNameExpression n) {
		this.transResult = n;
		// we won't visit the qualifier as it would lead us to another branch
	}

	public void visit(UnaryExpression n) {
		n.expr = translate(n.expr);
		
		this.transResult = n;
	}

	public void visit(VariableDeclarationExpression n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.type = translate(n.type);
		n.vars = translate(n.vars);

		this.transResult = n;
	}

	public void visit(BlockStatement n) {
		n.statements = translate(n.statements);
		
		this.transResult = n;
	}

	public void visit(EmptyStatement n) {
		this.transResult = n;
	}

	public void visit(ExpressionStatement n) {
		n.expr = translate(n.expr);
		
		this.transResult = n;
	}

	public void visit(ReturnStatement n) {
		n.expr = translate(n.expr);
		
		this.transResult = n;
	}

	public void visit(IfStatement n) {
		n.condition = translate(n.condition);
		n.thenStmt = translate(n.thenStmt);
		n.elseStmt = translate(n.elseStmt);
		
		this.transResult = n;
	}

	public void visit(WhileStatement n) {
		n.condition = translate(n.condition);
		n.body = translate(n.body);
		
		this.transResult = n;
	}

	public void visit(DoStatement n) {
		n.body = translate(n.body);
		n.condition = translate(n.condition);
		
		this.transResult = n;
	}

	public void visit(TransformationFieldDeclaration c) {
		c.modifiers = translateModifiers(c.modifiers);
		c.type = translate(c.type);
		c.ioParams = translate(c.ioParams);
		c.name = translateName(c.name);
		
		this.transResult = c;
	}

	public void visit(ImportDeclaration n) {
		n.name = translateName(n.name);
		this.transResult = n;
	}

	public void visit(FixedFieldDeclaration f) {
		f.name = translateName(f.name);
		f.type = translate(f.type);
		
		this.transResult = f;
	}

	public void visit(DelimitedFieldDeclaration f) {
		f.name = translateName(f.name);
		f.type = translate(f.type);
		
		this.transResult = f;
	}

	public void visit(ComponentFieldDeclaration n) {
		n.modifiers = translateModifiers(n.modifiers);
		n.type = translate(n.type);
		n.variable = translate(n.variable);
		
		this.transResult = n;
	}

	public void visit(ArrayAllocationExpression n) {
		n.type = translate(n.type);
		n.dimensions = translate(n.dimensions);
		n.initializer = translate(n.initializer);
		
		this.transResult = n;
	}
	

	public void visit(StreamOperationExpression n) {
		n.left = translate(n.left);
		n.right = translate(n.right);
		
		this.transResult = n;
	}

	public void visit(BreakStatement n) {
		this.transResult = n;
	}

	public void visit(CatchClause n) {
		n.except = translate(n.except);
		n.catchBlock = translate(n.catchBlock);
		
		this.transResult = n;
	}

	public void visit(ConnectStatement n) {
		n.sourcePort = translate(n.sourcePort);
		n.destPort = translate(n.destPort);
		
		this.transResult = n;
	}

	public void visit(ContinueStatement n) {
		this.transResult = n;
	}

	public void visit(ForeachStatement n) {
		n.var = translate(n.var);
		n.iterable = translate(n.iterable);
		n.body = translate(n.body);
		
		this.transResult = n;
	}

	public void visit(ForStatement n) {
		n.init = translate(n.init);
		n.iterable = translate(n.iterable);
		n.body = translate(n.body);
		
		this.transResult = n;
	}

	public void visit(SwitchEntryStatement n) {
		n.label = translate(n.label);
		n.stmts = translate(n.stmts);
		
		this.transResult = n;
	}

	public void visit(SwitchStatement n) {
		n.selector = translate(n.selector);
		n.entries = translate(n.entries);
		
		this.transResult = n;
	}

	public void visit(ThrowStatement n) {
		n.expr = translate(n.expr);
		
		this.transResult = n;
		
	}

	public void visit(TryStatement n) {
		n.tryBlock = translate(n.tryBlock);
		n.catchs = translate(n.catchs);
		n.finallyBlock = translate(n.finallyBlock);
		
		this.transResult = n;
	}

}
