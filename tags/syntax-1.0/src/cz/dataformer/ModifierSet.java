package cz.dataformer;

public class ModifierSet {
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

	/**
	 * A set of accessors that indicate whether the specified modifier is in the
	 * set.
	 */

	public boolean isPublic(int modifiers) {
		return (modifiers & PUBLIC) != 0;
	}

	public boolean isProtected(int modifiers) {
		return (modifiers & PROTECTED) != 0;
	}

	public boolean isPrivate(int modifiers) {
		return (modifiers & PRIVATE) != 0;
	}

	public boolean isStatic(int modifiers) {
		return (modifiers & STATIC) != 0;
	}

	public boolean isAbstract(int modifiers) {
		return (modifiers & ABSTRACT) != 0;
	}

	public boolean isFinal(int modifiers) {
		return (modifiers & FINAL) != 0;
	}

	public boolean isNative(int modifiers) {
		return (modifiers & NATIVE) != 0;
	}

	public boolean isStrictfp(int modifiers) {
		return (modifiers & STRICTFP) != 0;
	}

	public boolean isSynchronized(int modifiers) {
		return (modifiers & SYNCHRONIZED) != 0;
	}

	public boolean isTransient(int modifiers) {
		return (modifiers & TRANSIENT) != 0;
	}

	public boolean isVolatile(int modifiers) {
		return (modifiers & VOLATILE) != 0;
	}

	public boolean isInput(int modifiers) {
		return (modifiers & IN) != 0;
	}
	
	public boolean isOutput(int modifiers) {
		return (modifiers & OUT) != 0;
	}
	
	public boolean isOptional(int modifiers) {
		return (modifiers & OPTIONAL) != 0;
	}
	
	public boolean isRequired(int modifiers) {
		return (modifiers & REQUIRED) != 0;
	}
	
	
	
	/**
	 * Removes the given modifier.
	 */
	static int removeModifier(int modifiers, int mod) {
		return modifiers & ~mod;
	}
}
