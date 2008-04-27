package cz.dataformer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cz.dataformer.ast.ComponentDeclaration;
import cz.dataformer.ast.NodeVisitorImpl;
import cz.dataformer.ast.Transformation;
import cz.dataformer.ast.body.VariableDeclaratorId;
import cz.dataformer.ast.record.RecordDeclaration;

public class GraphRunner {

	private static class VisitorTest extends NodeVisitorImpl {
		
		public void visit(RecordDeclaration r) {
			System.out.println("Visited record: " + r.name);
		}
		
		public void visit(ComponentDeclaration n) {
			System.out.println("Visited component: " + n.name);
		}

		public void visit(VariableDeclaratorId n) {
			System.out.println("Local variable declaration: " + n.name);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GraphParser parser = new GraphParser(
					new FileInputStream("C:/Local/eclipse/workspace/DataFormer/src/GraphDefinition.def"));
			Transformation trans = parser.Transformation();
		
			
			VisitorTest test = new VisitorTest();
			trans.accept(test);
			
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
