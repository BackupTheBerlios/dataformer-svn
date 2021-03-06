package cz.dataformer;

import java.io.File;
import java.io.IOException;

public class RecordClassVisitor extends FileWriterVisitor {

	private ASTRecordDeclaration record;
	
	public RecordClassVisitor() {
		super();
		this.record = GeneratorProperties.ROOT;
	}

	/**
	 * Creates the class file and associated writer.
	 * Class name is the same as record name.
	 * 
	 * @throws VisitorException
	 */
	public void initClass() throws VisitorException {
		try {
			String className = record.name;
			openFile(new File(GeneratorProperties.PACKAGE_NAME.replace(".","/"),className+".java"));
			writeToken("package " + GeneratorProperties.PACKAGE_NAME + ";");
		} catch (IOException e) {
			throw new VisitorException("Can't open class file",e);
		}
	}
	
	public Object visit(SimpleNode node, Object data) {
		// not used
		return null;
	}

	/**
	 * Generates class named as record node passed as parameter.
	 * Enforces visiting all field nodes under record.
	 */
	public Object visit(ASTRecordDeclaration node, Object data) throws VisitorException {
		try {
			writeToken("public class " + node.name + " extends SimpleNode {");
			writeToken("public  " + node.name + "(int i) { super(i); }");
			if (node.children != null) {
				for (Node child : node.children) {
					((RecordField)child).jjtAccept(this,data);
				}
			}
			writeToken("}");
		} catch (IOException e) {
			throw new VisitorException("Can't write token",e);
		}
		
		return data;
	}

	/**
	 * Generates class field for given record field.
	 */
	public Object visit(ASTFixedRecordField node, Object data) throws VisitorException {
		try {
			writeToken("public " + node.getFieldTypeAsString() 
			           + " " + node.name + ";");
		} catch (IOException e) {
			throw new VisitorException("Can't write token",e);
		}
		return data;
	}

	public Object visit(ASTDelimitedRecordField node, Object data) throws VisitorException {
		try {
			writeToken("public " + node.getFieldTypeAsString() 
			           + " " + node.name + ";");
		} catch (IOException e) {
			throw new VisitorException("Can't write token",e);
		}
		return data;
	}

	public Object visit(ASTTransformation node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTComponentDeclaration node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTComponentConnection node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTModifiers node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTJavaTypeParameters node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTComponentTypeParameters node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTTypeParameter node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTTypeBound node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTClassOrInterfaceType node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTTypeArguments node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTTypeArgument node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTPorts node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTPort node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTProperties node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(ASTProperty node, Object data) throws VisitorException {
		// TODO Auto-generated method stub
		return null;
	}
	
		
}
