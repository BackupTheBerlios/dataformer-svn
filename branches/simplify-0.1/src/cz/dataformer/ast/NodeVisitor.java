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
 * @author mtomcany
 */
public interface NodeVisitor {

    public void visit(DataFormerNode n) throws GraphCompilerException;

    //- Compilation Unit ----------------------------------

    public void visit(ComponentDeclaration n) throws GraphCompilerException;

    public void visit(TransformationFieldDeclaration c) throws GraphCompilerException;
    
    public void visit(ImportDeclaration n) throws GraphCompilerException;

    public void visit(Transformation n) throws GraphCompilerException;


//    public void visit(TypeParameter n) throws GraphCompilerException;

    //- Record --------------------------------------------
    
    public void visit(RecordDeclaration r) throws GraphCompilerException;
    
    public void visit(FixedFieldDeclaration f) throws GraphCompilerException;
    
    public void visit(DelimitedFieldDeclaration f) throws GraphCompilerException;
    
    //- Body ----------------------------------------------

    public void visit(ComponentFieldDeclaration n) throws GraphCompilerException;

    public void visit(MainBlock n) throws GraphCompilerException;

    public void visit(MethodDeclaration n) throws GraphCompilerException;

    public void visit(Parameter n) throws GraphCompilerException;

    public void visit(Port n) throws GraphCompilerException;

    public void visit(VariableDeclarator n) throws GraphCompilerException;

    //- Type ----------------------------------------------

    public void visit(DataRecordType n) throws GraphCompilerException;

    public void visit(IOTypeParameter n) throws GraphCompilerException;
    
    public void visit(PrimitiveType n) throws GraphCompilerException;

    public void visit(ReferenceType n) throws GraphCompilerException;

    public void visit(VoidType n) throws GraphCompilerException;

    //- Expression ----------------------------------------

    public void visit(ArrayAccessExpression n) throws GraphCompilerException;

    public void visit(ArrayAllocationExpression n) throws GraphCompilerException;

    public void visit(ArrayInitializerExpression n) throws GraphCompilerException;

    public void visit(AssignmentExpression n) throws GraphCompilerException;

    public void visit(BinaryExpression n) throws GraphCompilerException;

    public void visit(BooleanLiteralExpression n) throws GraphCompilerException;

    public void visit(ConditionalExpression n) throws GraphCompilerException;

    public void visit(FieldAccessExpression n) throws GraphCompilerException;

    public void visit(IntegerLiteralExpression n) throws GraphCompilerException;

    public void visit(IntegerLiteralMinValueExpression n) throws GraphCompilerException;

    public void visit(LongLiteralExpression n) throws GraphCompilerException;

    public void visit(LongLiteralMinValueExpression n) throws GraphCompilerException;
    
    public void visit(MethodCallExpression n) throws GraphCompilerException;

    public void visit(NameExpression n) throws GraphCompilerException;

    public void visit(StreamOperationExpression n) throws GraphCompilerException;

    public void visit(StringLiteralExpression n) throws GraphCompilerException;

    public void visit(UnaryExpression n) throws GraphCompilerException;
    
    public void visit(VariableDeclarationExpression n) throws GraphCompilerException;
//TODO    public void visit(CharLiteralExpr n) throws GraphCompilerException;
//TODO    public void visit(DoubleLiteralExpr n) throws GraphCompilerException;
//TODO    public void visit(NullLiteralExpr n) throws GraphCompilerException;

    //- Statements ----------------------------------------

    public void visit(BlockStatement n) throws GraphCompilerException;

    public void visit(BreakStatement n) throws GraphCompilerException;

    public void visit(CatchClause n) throws GraphCompilerException;

    public void visit(ConnectStatement n) throws GraphCompilerException;
    
    public void visit(ContinueStatement n) throws GraphCompilerException;
    
    public void visit(DoStatement n) throws GraphCompilerException;

    public void visit(EmptyStatement n) throws GraphCompilerException;

    public void visit(ExpressionStatement n) throws GraphCompilerException;

    public void visit(ForeachStatement n) throws GraphCompilerException;

    public void visit(ForStatement n) throws GraphCompilerException;
    
    public void visit(IfStatement n) throws GraphCompilerException;
    
    public void visit(ReturnStatement n) throws GraphCompilerException;

    public void visit(SwitchEntryStatement n) throws GraphCompilerException;
    
    public void visit(SwitchStatement n) throws GraphCompilerException;

    public void visit(ThrowStatement n) throws GraphCompilerException;
    
    public void visit(TryStatement n) throws GraphCompilerException;

    public void visit(WhileStatement n) throws GraphCompilerException;

    // TODO   public void visit(LabeledStatement n) throws GraphCompilerException;
    // TODO   public void visit(SynchronizedStatement n) throws GraphCompilerException;
}
