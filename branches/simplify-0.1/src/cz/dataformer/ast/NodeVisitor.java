/*
 * Created on 05/10/2006
 */
package cz.dataformer.ast;

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
 * @author mtomcany
 */
public interface NodeVisitor {

    public void visit(DataFormerNode n);

    //- Compilation Unit ----------------------------------

    public void visit(ComponentDeclaration n);

    public void visit(TransformationFieldDeclaration c);
    
    public void visit(ImportDeclaration n);

    public void visit(Transformation n);


//    public void visit(TypeParameter n);

    //- Record --------------------------------------------
    
    public void visit(RecordDeclaration r);
    
    public void visit(FixedFieldDeclaration f);
    
    public void visit(DelimitedFieldDeclaration f);
    
    //- Body ----------------------------------------------

    public void visit(ComponentFieldDeclaration n);

    public void visit(MainBlock n);

    public void visit(MethodDeclaration n);

    public void visit(Parameter n);

    public void visit(Port n);

    public void visit(VariableDeclarator n);

    //- Type ----------------------------------------------

    public void visit(DataRecordType n);

    public void visit(IOTypeParameter n);
    
    public void visit(PrimitiveType n);

    public void visit(ReferenceType n);

    public void visit(VoidType n);

    //- Expression ----------------------------------------

    public void visit(ArrayAccessExpression n);

    public void visit(ArrayAllocationExpression n);

    public void visit(ArrayInitializerExpression n);

    public void visit(AssignmentExpression n);

    public void visit(BinaryExpression n);

    public void visit(BooleanLiteralExpression n);

    public void visit(ConditionalExpression n);

    public void visit(FieldAccessExpression n);

    public void visit(IntegerLiteralExpression n);

    public void visit(IntegerLiteralMinValueExpression n);

    public void visit(LongLiteralExpression n);

    public void visit(LongLiteralMinValueExpression n);
    
    public void visit(MethodCallExpression n);

    public void visit(NameExpression n);

    public void visit(QualifiedNameExpression n);
    
    public void visit(StreamOperationExpression n);

    public void visit(StringLiteralExpression n);

    public void visit(UnaryExpression n);
    
    public void visit(VariableDeclarationExpression n);
//TODO    public void visit(CharLiteralExpr n);
//TODO    public void visit(DoubleLiteralExpr n);
//TODO    public void visit(NullLiteralExpr n);

    //- Statements ----------------------------------------

    public void visit(BlockStatement n);

    public void visit(BreakStatement n);

    public void visit(CatchClause n);

    public void visit(ConnectStatement n);
    
    public void visit(ContinueStatement n);
    
    public void visit(DoStatement n);

    public void visit(EmptyStatement n);

    public void visit(ExpressionStatement n);

    public void visit(ForeachStatement n);

    public void visit(ForStatement n);
    
    public void visit(IfStatement n);
    
    public void visit(ReturnStatement n);

    public void visit(SwitchEntryStatement n);
    
    public void visit(SwitchStatement n);

    public void visit(ThrowStatement n);
    
    public void visit(TryStatement n);

    public void visit(WhileStatement n);

    // TODO   public void visit(LabeledStatement n);
    // TODO   public void visit(SynchronizedStatement n);
}
