package cz.dataformer;

import java.io.IOException;
import java.util.HashSet;


public class TokenVisitor extends FileWriterVisitor implements GraphParserVisitor {

	private final ASTRecordDeclaration record;
	private final String packageName;
	
	
	public TokenVisitor() {
		record = GeneratorProperties.ROOT;
		packageName = GeneratorProperties.PACKAGE_NAME;
	}

	
	public void init() throws IOException {
		appendFile(packageName.replace(".","/") + "/" + record.name + ".jjt" );
		writeToken("SKIP : { \" \"|\"\\r\"|\"\\t\"|\"\\f\" }");
		writeToken("TOKEN: { < #DIGIT: [\"0\"-\"9\"] > }");
		writeToken("TOKEN: { < BOOLEAN: \"true\" | \"false\"  > }");
		writeToken("TOKEN: { < INT: (<DIGIT>)+ > }");
		
	}
	
	public Object visit(SimpleNode node, Object data) throws VisitorException {
		// not used
		return null;
	}

	public Object visit(ASTRecordDeclaration node, Object data) throws VisitorException {
		if (node.children != null) {
			// set of tokens that were already generated
			HashSet<String> tokens = new HashSet<String>(); 
			for (Node child : node.children) {
				child.jjtAccept(this,tokens);
			}
		}
		
		return data;
	}

	public Object visit(ASTFixedRecordField node, Object data) throws VisitorException {
		@SuppressWarnings("unchecked")
		HashSet<String> usedTokens = (HashSet<String>)data;
		try {
			switch (node.type) {
			case GraphParserConstants.INTEGER:
				StringBuffer buf = new StringBuffer("TOKEN: { < ");
				String tokenName = "INT" + node.length;
				if (usedTokens.contains(tokenName)) {
					return data;
				}
				
				buf.append(tokenName).append(": (");
				for (int i=0; i<node.length; i++) {
					buf.append("<DIGIT>");
				}
				buf.append(")>}");
				writeToken(buf.toString());
				usedTokens.add(tokenName);
				break;
			case GraphParserConstants.BOOLEAN:
				if (node.length < 5) {
					throw new VisitorException("Boolean fixed field length " + node.length + 
							" is insufficient to hold value of 'false' (5 characters) ");
				}
				break;

			default:
				throw new VisitorException("Unknown field type");
			}
		} catch (IOException e) {
			throw new VisitorException("Can't write token",e);
		}

		return null;
		
	}

	public Object visit(ASTDelimitedRecordField node, Object data) throws VisitorException {
		return data;
	}
}
