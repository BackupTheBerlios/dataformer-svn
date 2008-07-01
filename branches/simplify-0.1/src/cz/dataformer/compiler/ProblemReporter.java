package cz.dataformer.compiler;

import java.util.LinkedList;

import cz.dataformer.DataFormerNode;
import cz.dataformer.ParseException;
import cz.dataformer.Token;
import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.body.Port;
import cz.dataformer.ast.expression.Expression;
import cz.dataformer.ast.expression.FieldAccessExpression;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;
import cz.dataformer.ast.type.IOTypeParameter;
import cz.dataformer.ast.type.Type;
import cz.dataformer.compiler.symbol.TypeSymbol;

public class ProblemReporter {

	private static final ProblemReporter INSTANCE = new ProblemReporter();

	private LinkedList<ProblemMessage> messages = new LinkedList<ProblemMessage>();
	
	private final class ProblemMessage {
		private final int line;
		private final int column;
		private final String message;
		
		public ProblemMessage(final int line, final int column, final String message) {
			this.line = line;
			this.column = column;
			this.message = message;
		}
	}

	public static ProblemReporter getInstance() {
		return INSTANCE;
	}
	
	public void fieldTypeCannotBeComposite(FieldDeclaration field) {
		ProblemMessage msg = 
			new ProblemMessage(field.getLine(),field.getColumn(),
			"Field type cannot be a composite type");
		messages.add(msg);
	}

	/**
	 * File for given transformation was not found
	 * @param err	errorneous transformation entry
	 */
	public void fileNotFound(XformEntry err) {
		ProblemMessage msg = 
			new ProblemMessage(-1,-1,"File for transformation not found: " 
					+	err.getFqn());
		messages.add(msg);
	}

	/**
	 * Unable to parse given transformation file
	 * @param entry 	errorneous transformation entry
	 */
	public void canNotParse(XformEntry entry, ParseException e) {
		final Token tok = e.currentToken; 
		String message = "Can't parse file: '" 
			+ entry.getResolvedPath().getAbsolutePath() 
			+ "'"
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
				n.ioType + "does not match I/O parameters declarated by component");
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
	
	public void expressionNotPortReference(Expression e) {
		ProblemMessage msg = new ProblemMessage(e.line,e.column,"Expression does not resolve to a valid port reference");
		messages.add(msg);
	}

	public void cannotConvertType(int line, int column, TypeSymbol from, TypeSymbol to) {
		ProblemMessage msg = new ProblemMessage(line,column,"Cannot convert " + from.getName() + " to " + to.getName());
		messages.add(msg);
	}

	
	public void fieldCannotBeResolved(FieldAccessExpression n) {
		ProblemMessage msg = new ProblemMessage(n.line,n.column,"Field cannot be resolved");
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
			for (ProblemMessage msg : messages) {
				if (msg.line > 0) {
					if (msg.column > 0) {
						System.out.println(msg.message + " at line " 
								+ msg.line + " column " + msg.column);
						continue;
					}
					System.out.println(msg.message + "at line "	+ msg.line);
					continue;
				}
				System.out.println(msg.message);
			}
		} else {
			System.out.println("Compiled successfully");
		}
	}












	
}
