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
import cz.dataformer.compiler.GraphCompilerException;

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

	public void visit(DataFormerNode n) throws GraphCompilerException {
		assert false : "Unreachable code";
	}

	/** Visitor method */
    public <T extends DataFormerNode> void visitNode(T node) throws GraphCompilerException {
        if (node != null) {
            node.accept(this);
        }
    }
    
    /** Visitor method for list of nodes */
    public <T extends DataFormerNode> void visitNode(List<T> nodeList) throws GraphCompilerException {
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
	public void visit(Transformation n) throws GraphCompilerException {
		visitNode(n.pkg);
		visitNode(n.imports);
		visitNode(n.records);
		visitNode(n.components);
		visitNode(n.variables);
		visitNode(n.graph);
	}

	public void visit(RecordDeclaration r) throws GraphCompilerException {
		visitNode(r.pkg);
		visitNode(r.imports);
		visitNode(r.fields);
	}
	
	public void visit(ComponentDeclaration n) throws GraphCompilerException {
		visitNode(n.pkg);
		visitNode(n.imports);
		visitNode(n.ioParams);
		visitNode(n.members);
		visitNode(n.main);
	}

	public void visit(MainBlock n) throws GraphCompilerException {
		visitNode(n.block);		
	}

	public void visit(MethodDeclaration n) throws GraphCompilerException {
		visitNode(n.returnType);
		visitNode(n.parameters);
		visitNode(n.throws_);
		visitNode(n.block);
	}

	public void visit(Parameter n) throws GraphCompilerException {
		visitNode(n.type);
	}

	public void visit(Port n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(VariableDeclarator n) throws GraphCompilerException {
		visitNode(n.init);
	}

	public void visit(DataRecordType n) throws GraphCompilerException {
		// we won't visit n.scope, as it would lead us to some other branch
	}

	public void visit(IOTypeParameter n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(PrimitiveType n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(ReferenceType n) throws GraphCompilerException {
		visitNode(n.type);
	}

	public void visit(VoidType n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(ArrayAccessExpression n) throws GraphCompilerException {
		visitNode(n.name);
		visitNode(n.index);
	}

	public void visit(ArrayInitializerExpression n) throws GraphCompilerException {
		visitNode(n.values);		
	}

	public void visit(AssignmentExpression n) throws GraphCompilerException {
		visitNode(n.target);
		visitNode(n.value);		
	}

	public void visit(BinaryExpression n) throws GraphCompilerException {
		visitNode(n.left);
		visitNode(n.right);
	}

	public void visit(ConditionalExpression n) throws GraphCompilerException {
		visitNode(n.condition);
		visitNode(n.thenExpr);
		visitNode(n.elseExpr);
	}

	public void visit(FieldAccessExpression n) throws GraphCompilerException {
		// we won't visit n.scope  as it would lead us to another branch
	}

	public void visit(StringLiteralExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(IntegerLiteralExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(LongLiteralExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(IntegerLiteralMinValueExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(LongLiteralMinValueExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(BooleanLiteralExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(MethodCallExpression n) throws GraphCompilerException {
		visitNode(n.args);
		// we won't visit n.scope as it would lead us to another branch
	}

	public void visit(NameExpression n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(UnaryExpression n) throws GraphCompilerException {
		visitNode(n.expr);
		
	}

	public void visit(VariableDeclarationExpression n) throws GraphCompilerException {
		visitNode(n.type);
		visitNode(n.vars);
	}

	public void visit(BlockStatement n) throws GraphCompilerException {
		visitNode(n.statements);
	}

	public void visit(EmptyStatement n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(ExpressionStatement n) throws GraphCompilerException {
		visitNode(n.expr);
	}

	public void visit(ReturnStatement n) throws GraphCompilerException {
		visitNode(n.expr);
	}

	public void visit(IfStatement n) throws GraphCompilerException {
		visitNode(n.condition);
		visitNode(n.thenStmt);
		visitNode(n.elseStmt);
	}

	public void visit(WhileStatement n) throws GraphCompilerException {
		visitNode(n.condition);
		visitNode(n.body);
	}

	public void visit(DoStatement n) throws GraphCompilerException {
		visitNode(n.body);
		visitNode(n.condition);
	}

	public void visit(TransformationFieldDeclaration c) throws GraphCompilerException {
		visitNode(c.type);
		visitNode(c.ioParams);
	}

	public void visit(ImportDeclaration n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(FixedFieldDeclaration f) throws GraphCompilerException {
		visitNode(f.type);
	}

	public void visit(DelimitedFieldDeclaration f) throws GraphCompilerException {
		visitNode(f.type);		
	}

	public void visit(ComponentFieldDeclaration n) throws GraphCompilerException {
		visitNode(n.type);
		visitNode(n.variable);
	}

	public void visit(ArrayAllocationExpression n) throws GraphCompilerException {
		visitNode(n.type);
		visitNode(n.dimensions);
		visitNode(n.initializer);		
	}

	public void visit(StreamOperationExpression n) throws GraphCompilerException {
		visitNode(n.left);
		visitNode(n.right);
	}

	public void visit(BreakStatement n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(CatchClause n) throws GraphCompilerException {
		visitNode(n.except);
		visitNode(n.catchBlock);
	}

	public void visit(ConnectStatement n) throws GraphCompilerException {
		visitNode(n.sourcePort);
		visitNode(n.destPort);
	}

	public void visit(ContinueStatement n) throws GraphCompilerException {
		// nothing to do
	}

	public void visit(ForeachStatement n) throws GraphCompilerException {
		visitNode(n.var);
		visitNode(n.iterable);
		visitNode(n.body);
	}

	public void visit(ForStatement n) throws GraphCompilerException {
		visitNode(n.init);
		visitNode(n.iterable);
		visitNode(n.body);
	}

	public void visit(SwitchEntryStatement n) throws GraphCompilerException {
		visitNode(n.label);
		visitNode(n.stmts);		
	}

	public void visit(SwitchStatement n) throws GraphCompilerException {
		visitNode(n.selector);
		visitNode(n.entries);
	}

	public void visit(ThrowStatement n) throws GraphCompilerException {
		visitNode(n.expr);		
	}

	public void visit(TryStatement n) throws GraphCompilerException {
		visitNode(n.tryBlock);
		visitNode(n.catchs);
		visitNode(n.finallyBlock);
	}

}
