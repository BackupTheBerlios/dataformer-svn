package cz.dataformer;


public class DataFormerNode {

    private final int line;

    private final int column;

    /**
     * This attribute can store additional information from semantic analysis.
     */
    public Object data;

    public DataFormerNode(int line, int column) {
        this.line = line;
        this.column = column;
    }
    
    public int getLine() {
		return line;
	}
    
    public int getColumn() {
		return column;
	}
}
