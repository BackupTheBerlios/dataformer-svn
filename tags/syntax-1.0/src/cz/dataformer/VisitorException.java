package cz.dataformer;

public class VisitorException extends Exception {

	private static final long serialVersionUID = 7703498478854976977L;

	public VisitorException(Throwable t) {
		super(t);
	}
	
	public VisitorException(String message, Throwable t) {
		super(message,t);
	}
	
	public VisitorException(String message) {
		super(message);
	}
}
