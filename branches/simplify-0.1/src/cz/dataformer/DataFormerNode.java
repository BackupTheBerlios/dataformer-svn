package cz.dataformer;

import cz.dataformer.ast.NodeVisitor;
import cz.dataformer.compiler.GraphCompilerException;


public abstract class DataFormerNode  {

    public final int line;
    public final int column;

    public DataFormerNode(int line, int column) {
        this.line = line;
        this.column = column;
    }
    
    /** Visitor support method */
    public abstract void accept(NodeVisitor v) throws GraphCompilerException;
    
    
    public int getLine() {
		return line;
	}
    
    public int getColumn() {
		return column;
	}
}
