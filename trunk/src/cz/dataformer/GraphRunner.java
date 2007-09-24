package cz.dataformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GraphParser parser = new GraphParser(
					new FileInputStream("C:/Local/eclipse/workspace/DataFormer/src/GraphDefinition.def"));
//			parser.RecordDeclaration();
			parser.Transformation();
			((SimpleNode)parser.jjtree.rootNode()).dump("");
//			RecordParserGenerator gen = new RecordParserGenerator("cz.dataformer.metadata",
//					(ASTRecordDeclaration)parser.jjtree.rootNode());
//			gen.generateRecordParser();
		} catch (FileNotFoundException e) {
			System.err.println("Input file not found:" + e.getMessage());
		} catch (ParseException e) {
			System.err.println("Parsing error occured: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (VisitorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
