package cz.dataformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.BodyDeclaration;
import cz.dataformer.ast.record.FieldDeclaration;
import cz.dataformer.ast.record.RecordDeclaration;

public class GraphRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GraphParser parser = new GraphParser(
					new FileInputStream("C:/Local/eclipse/workspace/DataFormer/src/GraphDefinition.def"));
//			parser.RecordDeclaration();
			Transformation trans = parser.Transformation();
			for (ComponentDeclaration cd : trans.getComponents()) {
				System.out.println("Component: " + cd.getName());
				for (BodyDeclaration bd : cd.getMembers()) {
					System.out.println(bd.getClass().getCanonicalName());
				}
			}
			
			for (RecordDeclaration rd : trans.getRecords()) {
				System.out.println("record " + rd.name);
				for (FieldDeclaration fd : rd.fields) {
					System.out.println("\t field " + fd.getName());
				}
			}
//			((SimpleNode)parser.jjtree.rootNode()).dump("");
//			RecordParserGenerator gen = new RecordParserGenerator("cz.dataformer.metadata",
//					(ASTRecordDeclaration)parser.jjtree.rootNode());
//			gen.generateRecordParser();
		} catch (FileNotFoundException e) {
			System.err.println("Input file not found:" + e.getMessage());
		} catch (ParseException e) {
			System.err.println("Parsing error occured: " + e.getMessage());
		} 
//		catch (VisitorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
