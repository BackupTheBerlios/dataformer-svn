package cz.dataformer.body;

import cz.dataformer.ParseException;
import cz.dataformer.Token;


/**
 * Class represents set of modifiers related to language element
 * 
 * @author mtomcany
 *
 */
public class Modifiers {

	/* Definitions of the bits in the modifiers field. */
	public static final int PUBLIC = 0x00001;

	public static final int PROTECTED = 0x00002;

	public static final int PRIVATE = 0x00004;

	public static final int ABSTRACT = 0x00008;

	public static final int STATIC = 0x00010;

	public static final int FINAL = 0x00020;

	public static final int SYNCHRONIZED = 0x00040;

	public static final int NATIVE = 0x00080;

	public static final int TRANSIENT = 0x00100;

	public static final int VOLATILE = 0x00200;

	public static final int STRICTFP = 0x01000;

	
	
	// Dataformer modifiers
	public static final int IN = 0x02000;

	public static final int OUT = 0x04000;

	public static final int OPTIONAL = 0x08000;

	public static final int REQUIRED = 0x10000;

	
	// modifiers represented as a logical sum
	private final int modifiers;
	
	/**
	 * Creates instance with the defined set of modifiers
	 * @param modifiers	Modifiers to use
	 */
	public Modifiers(int modifiers) {
		this.modifiers = modifiers;
	}
	
	
	/**
	 * A set of accessors that indicate whether the specified modifier is in the
	 * set.
	 */

	public boolean isPublic() {
		return (modifiers & PUBLIC) != 0;
	}

	public boolean isProtected() {
		return (modifiers & PROTECTED) != 0;
	}

	public boolean isPrivate() {
		return (modifiers & PRIVATE) != 0;
	}

	public boolean isStatic() {
		return (modifiers & STATIC) != 0;
	}

	public boolean isAbstract() {
		return (modifiers & ABSTRACT) != 0;
	}

	public boolean isFinal() {
		return (modifiers & FINAL) != 0;
	}

	public boolean isNative() {
		return (modifiers & NATIVE) != 0;
	}

	public boolean isStrictfp() {
		return (modifiers & STRICTFP) != 0;
	}

	public boolean isSynchronized() {
		return (modifiers & SYNCHRONIZED) != 0;
	}

	public boolean isTransient() {
		return (modifiers & TRANSIENT) != 0;
	}

	public boolean isVolatile() {
		return (modifiers & VOLATILE) != 0;
	}

	public boolean isInput() {
		return (modifiers & IN) != 0;
	}
	
	public boolean isOutput() {
		return (modifiers & OUT) != 0;
	}
	
	public boolean isOptional() {
		return (modifiers & OPTIONAL) != 0;
	}
	
	public boolean isRequired() {
		return (modifiers & REQUIRED) != 0;
	}
	
	
	/**
	 * Removes the given modifier.
	 */
	public static int removeModifier(int modifiers, int mod) {
		return modifiers & ~mod;
	}
	
	 /**
     * Adds the given modifier.
     */
    public static int addModifier(int modifiers, int mod, Token token) throws ParseException {
        if ((modifiers & mod) != 0) {
            throw new ParseException(token, "Duplicated modifier");
        }
        return modifiers |= mod;
    }
	
}
