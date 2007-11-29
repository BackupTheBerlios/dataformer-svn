package cz.dataformer.type;


/**
 * Represents type as from Java Generics
 * @author mtomcany
 *
 */
public class GenericType extends Type {

	private final String name;
	
	public GenericType(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	

}
