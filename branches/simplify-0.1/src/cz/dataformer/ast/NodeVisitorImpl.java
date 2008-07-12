package cz.dataformer.ast;

import java.util.List;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.ComponentFieldDeclaration;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.MethodDeclaration;
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
import cz.dataformer.ast.type.DataRecordType;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.ast.type.ReferenceType;
import cz.dataformer.ast.type.VoidType;

/**
 * No-op dummy implementation of NodeVisitor interface.
 * Clients may extend this class when only want to override some of visitor's methods
 *  
 * Guaranteed order of passage:<br> 
 * <ol>
 * <li>Package</li>
 * <li>Imports</li>
 * <li>Record declarations</li>
 * <li>Component declarations</li>
 * <li>Component variables (instantiations)</li>
 * <li>Transformation graph construction</li>
 * </ol>
 * @author mtomcany
 *
 */
public class NodeVisitorImpl implements NodeVisitor {

	public void visit(DataFormerNode n) {
		assert false : "Unreachable code";
	}

	/** Visitor method */
    public <T extends DataFormerNode> void visitNode(T node) {
        if (node != null) {
            node.accept(this);
        }
    }
    
    /** Visitor method for list of nodes */
    public <T extends DataFormerNode> void visitNode(List<T> nodeList) {
    	if (nodeList != null) {
    		for (T node : nodeList) {
    			visitNode(node);
    		}
    	}
    }
    
    /**
     * Order of passage must stay guaranteed
     * See {@link ASTAnnotator#DataRecordCollector}
     */
	public void visit(Transformation n) {
		visitNode(n.packageName);
		visitNode(n.imports);
		visitNode(n.records);
		visitNode(n.components);
		visitNode(n.variables);
		visitNode(n.graph);
	}

	public void visit(RecordDeclaration r) {
		visitNode(r.fields);
	}
	
	public void visit(ComponentDeclaration n) {
		visitNode(n.ioParams);
		visitNode(n.members);
		visitNode(n.main);
	}

	public void visit(MainBlock n) {
		visitNode(n.block);		
	}

	public void visit(MethodDeclaration n) {
		visitNode(n.returnType);
		visitNode(n.parameters);
		visitNode(n.throws_);
		visitNode(n.block);
	}

	public void visit(Parameter n) {
		visitNode(n.type);
	}

	public void visit(Port n) {
		// nothing to do
	}

	public void visit(VariableDeclarator n) {
		visitNode(n.init);
	}

	public void visit(DataRecordType n) {
		// we won't visit n.scope, as it would lead us to some other branch
	}

	public void visit(IOTypeParameter n) {
		// nothing to do
	}

	public void visit(PrimitiveType n) {
		// nothing to do
	}

	public void visit(ReferenceType n) {
		visitNode(n.type);
	}

	public void visit(VoidType n) {
		// nothing to do
	}

	public void visit(ArrayAccessExpression n) {
		visitNode(n.name);
		visitNode(n.index);
	}

	public void visit(ArrayInitializerExpression n) {
		visitNode(n.values);		
	}

	public void visit(AssignmentExpression n) {
		visitNode(n.target);
		visitNode(n.value);		
	}

	public void visit(BinaryExpression n) {
		visitNode(n.left);
		visitNode(n.right);
	}

	public void visit(ConditionalExpression n) {
		visitNode(n.condition);
		visitNode(n.thenExpr);
		visitNode(n.elseExpr);
	}

	public void visit(FieldAccessExpression n) {
		// we won't visit n.scope  as it would lead us to another branch
	}

	public void visit(StringLiteralExpression n) {
		// nothing to do
	}

	public void visit(IntegerLiteralExpression n) {
		// nothing to do
	}

	public void visit(LongLiteralExpression n) {
		// nothing to do
	}

	public void visit(IntegerLiteralMinValueExpression n) {
		// nothing to do
	}

	public void visit(LongLiteralMinValueExpression n) {
		// nothing to do
	}

	public void visit(BooleanLiteralExpression n) {
		// nothing to do
	}

	public void visit(MethodCallExpression n) {
		visitNode(n.args);
		// we won't visit n.scope as it would lead us to another branch
	}

	public void visit(NameExpression n) {
		// nothing to do
	}

	public void visit(QualifiedNameExpression n) {
		// nothing to do
	}

	public void visit(UnaryExpression n) {
		visitNode(n.expr);
		
	}

	public void visit(VariableDeclarationExpression n) {
		visitNode(n.type);
		visitNode(n.vars);
	}

	public void visit(BlockStatement n) {
		visitNode(n.statements);
	}

	public void visit(EmptyStatement n) {
		// nothing to do
	}

	public void visit(ExpressionStatement n) {
		visitNode(n.expr);
	}

	public void visit(ReturnStatement n) {
		visitNode(n.expr);
	}

	public void visit(IfStatement n) {
		visitNode(n.condition);
		visitNode(n.thenStmt);
		visitNode(n.elseStmt);
	}

	public void visit(WhileStatement n) {
		visitNode(n.condition);
		visitNode(n.body);
	}

	public void visit(DoStatement n) {
		visitNode(n.body);
		visitNode(n.condition);
	}

	public void visit(TransformationFieldDeclaration c) {
		visitNode(c.type);
		visitNode(c.ioParams);
	}

	public void visit(ImportDeclaration n) {
		// nothing to do
	}

	public void visit(FixedFieldDeclaration f) {
		visitNode(f.type);
	}

	public void visit(DelimitedFieldDeclaration f) {
		visitNode(f.type);		
	}

	public void visit(ComponentFieldDeclaration n) {
		visitNode(n.type);
		visitNode(n.variable);
	}

	public void visit(ArrayAllocationExpression n) {
		visitNode(n.type);
		visitNode(n.dimensions);
		visitNode(n.initializer);		
	}

	public void visit(StreamOperationExpression n) {
		visitNode(n.left);
		visitNode(n.right);
	}

	public void visit(BreakStatement n) {
		// nothing to do
	}

	public void visit(CatchClause n) {
		visitNode(n.except);
		visitNode(n.catchBlock);
	}

	public void visit(ConnectStatement n) {
		visitNode(n.sourcePort);
		visitNode(n.destPort);
	}

	public void visit(ContinueStatement n) {
		// nothing to do
	}

	public void visit(ForeachStatement n) {
		visitNode(n.var);
		visitNode(n.iterable);
		visitNode(n.body);
	}

	public void visit(ForStatement n) {
		visitNode(n.init);
		visitNode(n.iterable);
		visitNode(n.body);
	}

	public void visit(SwitchEntryStatement n) {
		visitNode(n.label);
		visitNode(n.stmts);		
	}

	public void visit(SwitchStatement n) {
		visitNode(n.selector);
		visitNode(n.entries);
	}

	public void visit(ThrowStatement n) {
		visitNode(n.expr);		
	}

	public void visit(TryStatement n) {
		visitNode(n.tryBlock);
		visitNode(n.catchs);
		visitNode(n.finallyBlock);
	}

}
