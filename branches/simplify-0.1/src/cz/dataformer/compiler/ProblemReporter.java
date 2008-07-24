package cz.dataformer.compiler;

import java.util.LinkedList;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ParseException;
import cz.dataformer.Token;
import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.ImportDeclaration;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.ast.expression.FieldAccessExpression;
import cz.dataformer.ast.expression.NameExpression;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.Type;

public class ProblemReporter {

	private static final ProblemReporter INSTANCE = new ProblemReporter();

	private LinkedList<ProblemMessage> messages = new LinkedList<ProblemMessage>();
	private String currentFile;
	
	
	private final class ProblemMessage {
		private final String file;
		private final int line;
		private final int column;
		private final String message;
		
		
		public ProblemMessage(final int line, final int column, final String message) {
			this.file = currentFile;
			this.line = line;
			this.column = column;
			this.message = message;
		}
	}

	public static ProblemReporter getInstance() {
		return INSTANCE;
	}
	
	public void setFile(String file) {
		this.currentFile = file;
	}
	
	public String getFile() {
		return this.currentFile;
	}
	
	public void fieldTypeCannotBeComposite(FieldDeclaration field) {
		ProblemMessage msg = 
			new ProblemMessage(field.getLine(),field.getColumn(),
			"Field type cannot be a composite type");
		messages.add(msg);
	}

	/**
	 * File for given transformation was not found
	 */
	public void fileNotFound() {
		ProblemMessage msg = 
			new ProblemMessage(-1,-1,"File not found");
		messages.add(msg);
	}

	/**
	 * Unable to parse given transformation file
	 */
	public void canNotParse(ParseException e) {
		final Token tok = e.currentToken; 
		String message = "Unable to parse: " 
			+ "\n\n"
			+ e.getMessage();
		
		ProblemMessage msg = new ProblemMessage(tok.beginLine,tok.beginColumn,message);
		messages.add(msg);
	}

	/**
	 * Illegal file name - too many dot characters are confusing for package resolution
	 * E.g. illlegal.file.name.def
	 * 
	 * @param fileName	illegal file name
	 */
	public void illegalFileName(String fileName) {
		ProblemMessage msg = new ProblemMessage(-1,-1,"Illegal transformation file name: '" + fileName + "'");
		messages.add(msg);
	}
	
	
	
	
	
	// Semantic analysis messages
	
	public void ioTypeCannotBeResolved(Port n) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,
				n.ioType + " cannot be resolved to an I/O parameter");
		messages.add(msg);
	}

	public void typeDoesNotMatchIOParams(Port n) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,
				n.ioType + " does not match I/O parameters declarated by component");
		messages.add(msg);
	}

	public void typeCannotBeResolved(DataFormerNode n, String typeName) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,
				typeName + " cannot be resolved to a valid type");
		messages.add(msg);
	}

	public void componentHasNoPorts(ComponentDeclaration n) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,
		"Component has no ports");
		messages.add(msg);
	}

	public void duplicateDeclaration(String message, DataFormerNode ast) {
		ProblemMessage msg = new ProblemMessage(ast.line,ast.column, message);
		messages.add(msg);
	}
	
	
	public void incorrectNumberOfIOParams(int line,int column,ComponentDeclaration n) {
		StringBuffer types = new StringBuffer("<");
		for (IOTypeParameter p : n.ioParams) {
			types.append(p.name).append(",");
		}
		types.deleteCharAt(types.length()-1);
		types.append(">");
		ProblemMessage msg = new ProblemMessage(line,column,"Incorrect number of I/O parameters for component " + n.name + types);
		messages.add(msg);
	}

	public void notValidRecordType(Type t) {
		ProblemMessage msg = new ProblemMessage(t.line,t.column,"Not a valid data record type");
		messages.add(msg);
	}

	public void recordHasNoFields(RecordDeclaration ast) {
		ProblemMessage msg = new ProblemMessage(ast.line,ast.column,"Data record has no fields declared");
		messages.add(msg);
	}

	public void recordCannotBeResolved(NameExpression name) {
		ProblemMessage msg = new ProblemMessage(name.line,name.column,name.name + " cannot be resolved to a data record");
		messages.add(msg);
	}
	
	public void expressionNotPortReference(Expression e) {
		ProblemMessage msg = new ProblemMessage(e.line,e.column,"Expression does not resolve to a valid port reference");
		messages.add(msg);
	}

	public void fieldCannotBeResolved(FieldAccessExpression n) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,"Field cannot be resolved");
		messages.add(msg);
	}

	public void collidingSingleImport(ImportDeclaration ast, ImportDeclaration collision) {
		ProblemMessage msg = new ProblemMessage(ast.line,ast.column,
				"Import statement collides with another import at line " 
				+ collision.line + " column " + collision.column);
		messages.add(msg);
	}
	
	public void recordCannotImport(ImportDeclaration ast, String illegalImportType ) {
		ProblemMessage msg = new ProblemMessage(ast.line,ast.column,
				"Record declaration can never import a " + illegalImportType);
		messages.add(msg);
	}

	public void componentCannotImportTransformation(ImportDeclaration ast) {
		ProblemMessage msg = new ProblemMessage(ast.line,ast.column,
				"Record declaration can never import a transformation");
		messages.add(msg);
	}

	
	// Other API
	
	/**
	 * @return	number of errors encountered
	 */
	public int errorCount() {
		return messages.size();
	}

	public void writeErrors() {
		if (errorCount() > 0) {
			StringBuffer buf = new StringBuffer();
			for (ProblemMessage msg : messages) {
				buf.delete(0, buf.length());
				buf.append(msg.message);
				if (msg.file != null) {
					buf.append(" in file " + msg.file);
				}
				if (msg.line > 0) {
					buf.append(" at line " + msg.line);
				}
				if (msg.column > 0) {
					buf.append(" column " + msg.column);
				}
				System.out.println(buf.toString());
			}
		} else {
			System.out.println("Compiled successfully");
		}
	}


	
}
