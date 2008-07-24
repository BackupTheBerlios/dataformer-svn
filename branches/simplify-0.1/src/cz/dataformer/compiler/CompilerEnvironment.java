package cz.dataformer.compiler;

import java.util.HashMap;

public class CompilerEnvironment {

	private static final CompilerEnvironment INSTANCE = new CompilerEnvironment();
	
	private HashMap<String,XformEntry> entries = new HashMap<String,XformEntry>();

	
	
	public static CompilerEnvironment getInstance() {
		return INSTANCE;
	}
	
	public XformEntry getEntry(String fqdn) {
		return entries.get(fqdn);
	}
	
	public void addEntry(XformEntry entry) {
		entries.put(entry.getFQDN(),entry);
	}
}
