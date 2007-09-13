package cz.dataformer;

import java.io.IOException;

public class RulesVisitor extends FileWriterVisitor implements GraphParserVisitor {

	
	private ASTRecordDeclaration record;
	private String packageName;

	public RulesVisitor() {
		record = GeneratorProperties.ROOT;
		packageName = GeneratorProperties.PACKAGE_NAME;
	}
	
	public void init() throws IOException {
		appendFile(packageName.replace(".","/") + "/" + record.name + ".jjt" );
	}
	
	public Object visit(SimpleNode node, Object data) {
		// not used
		return null;
	}

	public Object visit(ASTRecordDeclaration node, Object data) throws VisitorException {
		try {
		if (node.children != null) {
			writeToken("void " + node.name + "():");
			writeToken("{");
			for (Node child : node.children) {
				writeToken("Token " + ((RecordField)child).name + ";");
			}
			writeToken("}");
			writeToken("{");
			for (Node child : node.children) {
				child.jjtAccept(this,data);
			}
			writeToken("}");
		}
		} catch (IOException e) {
			throw new VisitorException("Can't write token",e);
		}
		
		return null;
	}

	public Object visit(ASTFixedRecordField node, Object data) throws VisitorException {
		try {
			switch (node.type) {
			case GraphParserConstants.INTEGER:
				writeToken(node.name + "=<INT" + node.length + ">");
				writeToken("{ jjtThis." + node.name + "=Integer.parseInt("
						+ node.name + ".image); }");
				break;

			case GraphParserConstants.BOOLEAN:
				writeToken(node.name + "=<BOOLEAN>");
				writeToken("{ jjtThis." + node.name + "=Boolean.valueOf("
						+ node.name + ".image); }");
				break;

			default:
					throw new VisitorException("Unknown field type");
			}
				
		} catch (IOException e) {
			throw new VisitorException("Can't write token", e);
		}
		
		return data;
	}

	public Object visit(ASTDelimitedRecordField node, Object data) throws VisitorException {
		try {
			switch (node.type) {
			case GraphParserConstants.INTEGER:
				writeToken(node.name + "=<INT>");
				writeToken("{ jjtThis." + node.name + "=Integer.parseInt("
						+ node.name + ".image); }");
				writeToken(node.delimiter);
				break;

			case GraphParserConstants.BOOLEAN:
				writeToken(node.name + "=<BOOLEAN>");
				writeToken("{ jjtThis." + node.name + "=Boolean.valueOf("
						+ node.name + ".image); }");
				writeToken(node.delimiter);
				break;
				
			default:
				throw new VisitorException("Unknown field type");
			}
				
		} catch (IOException e) {
			throw new VisitorException("Can't write token", e);
		}
		
		return null;
	}

}
