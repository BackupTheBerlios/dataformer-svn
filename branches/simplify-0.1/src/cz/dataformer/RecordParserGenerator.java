package cz.dataformer;

import java.io.IOException;

public class RecordParserGenerator {

	private ClassFileWriter writer;
	private ASTRecordDeclaration record;
	private String packageName;
	
	public RecordParserGenerator(String packageName, ASTRecordDeclaration rootRecord) 
	throws IOException {

		/* shared properties */
		GeneratorProperties.ROOT_DIRECTORY = "C:/Local/eclipse/workspace/DataFormer/generator"; 
		GeneratorProperties.PACKAGE_NAME = packageName;
		GeneratorProperties.ROOT = rootRecord;

		this.packageName = packageName;
		this.record = rootRecord;
		this.writer = new ClassFileWriter(GeneratorProperties.ROOT_DIRECTORY);
	}

	public void generateRecordParser() throws IOException, VisitorException {
		generateRecordClass();
		generateHeader();
		generateTokens();
		generateRules();
	}
	
	private void generateRecordClass() throws VisitorException {
		RecordClassVisitor visitor = new RecordClassVisitor();
		visitor.initClass();
		record.jjtAccept(visitor,null);
		visitor.closeFile();
	}
	
	private void generateHeader() throws IOException {
		writer.openFile(packageName.replace(".","/") + "/" + record.name + ".jjt" );
		writeToken("options {");
		writeToken("JDK_VERSION = \"1.5\";");
		writeToken("STATIC = false;");
		writeToken("MULTI=true;");
		writeToken("NODE_PREFIX=\"\";");
		writeToken("}");
		
		// header PARSER_BEGIN ... PARSER_END
		writeToken("PARSER_BEGIN(" + record.getName() + "Parser)");
		writeToken("package " + this.packageName + ";");
		writeToken("public class " + record.getName() + "Parser {");
		writeToken("}");
		writeToken("PARSER_END(" + record.getName() + "Parser)");
		writer.closeFile();
	}
	
	
	private void generateTokens() throws VisitorException, IOException {
		TokenVisitor visitor = new TokenVisitor();
		visitor.init();
		record.jjtAccept(visitor,null);
		visitor.closeFile();
	}
	
	private void generateRules() throws IOException, VisitorException {
		RulesVisitor visitor = new RulesVisitor();
		visitor.init();
		record.jjtAccept(visitor,null);
		visitor.closeFile();
	}
	
	private void writeToken(String token) throws IOException {
		writer.writeToken(token);
	}

	
	
}
