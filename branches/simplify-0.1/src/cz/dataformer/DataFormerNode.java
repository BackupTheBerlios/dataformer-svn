package cz.dataformer;

import cz.dataformer.ast.NodeVisitor;


public abstract class DataFormerNode {

    public final int line;
    public final int column;

    public DataFormerNode(int line, int column) {
        this.line = line;
        this.column = column;
    }
    
    /** Visitor support method */
    public abstract void accept(NodeVisitor v);
    
    
    public int getLine() {
		return line;
	}
    
    public int getColumn() {
		return column;
	}
}
