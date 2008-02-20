package cz.dataformer.ast;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ast.body.ComponentProperty;
import cz.dataformer.ast.body.MainBlock;
import cz.dataformer.ast.body.MethodDeclaration;
import cz.dataformer.ast.body.Parameter;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.body.VariableDeclarator;
import cz.dataformer.ast.body.VariableDeclaratorId;
import cz.dataformer.ast.expression.ArrayAccessExpression;
import cz.dataformer.ast.expression.ArrayInitializerExpr;
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
import cz.dataformer.ast.expression.StringLiteralExpression;
import cz.dataformer.ast.expression.UnaryExpression;
import cz.dataformer.ast.expression.VariableDeclarationExpression;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.statement.BlockStatement;
import cz.dataformer.ast.statement.DoStatement;
import cz.dataformer.ast.statement.EmptyStatement;
import cz.dataformer.ast.statement.ExpressionStatement;
import cz.dataformer.ast.statement.IfStatement;
import cz.dataformer.ast.statement.ReturnStatement;
import cz.dataformer.ast.statement.WhileStatement;
import cz.dataformer.ast.type.ClassOrInterfaceType;
import cz.dataformer.ast.type.GenericType;
import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.ast.type.ReferenceType;
import cz.dataformer.ast.type.VoidType;
import cz.dataformer.ast.type.WildcardType;

/**
 * No-op dummy implementation of NodeVisitor interface.
 * Clients may extend this class when only want to override some of visitor's methods
 *  
 * @author mtomcany
 *
 */
public class NodeVisitorImpl implements NodeVisitor {

	public void visit(DataFormerNode n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Transformation n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(RecordDeclaration r) {
		// TODO Auto-generated method stub
	}
	
	public void visit(ComponentDeclaration n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ComponentProperty n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MainBlock n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MethodDeclaration n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Parameter n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Port n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(VariableDeclarator n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(VariableDeclaratorId n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ClassOrInterfaceType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(GenericType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(PrimitiveType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ReferenceType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(VoidType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(WildcardType n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ArrayAccessExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ArrayInitializerExpr n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(AssignmentExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BinaryExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ConditionalExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(FieldAccessExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(StringLiteralExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IntegerLiteralExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(LongLiteralExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IntegerLiteralMinValueExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(LongLiteralMinValueExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BooleanLiteralExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(MethodCallExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(NameExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(QualifiedNameExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(UnaryExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(VariableDeclarationExpression n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(BlockStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(EmptyStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ExpressionStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(ReturnStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(IfStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(WhileStatement n) {
		// TODO Auto-generated method stub
		
	}

	public void visit(DoStatement n) {
		// TODO Auto-generated method stub
		
	}

}
