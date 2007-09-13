package cz.dataformer;

public class ASTRecordDeclaration extends SimpleNode {

	String name;

	public ASTRecordDeclaration(int id) {
		super(id);
	}

	public ASTRecordDeclaration(GraphParser p, int id) {
		super(p, id);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public Object jjtAccept(GraphParserVisitor visitor, Object data) throws VisitorException  {
		visitor.visit(this,data);
		return data;
	}

}
