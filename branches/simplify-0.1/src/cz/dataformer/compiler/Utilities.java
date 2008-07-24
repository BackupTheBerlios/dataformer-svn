package cz.dataformer.compiler;

import cz.dataformer.ast.type.PrimitiveType;
import cz.dataformer.compiler.model.PrimitiveTypeModel;

public final class Utilities {

	private Utilities() {
		// not available
	}

	public static PrimitiveTypeModel typeEnumToModel(PrimitiveType ast) {
		switch (ast.type) {
		case Int:
			return PrimitiveTypeModel.INT;
		case Boolean:
			return PrimitiveTypeModel.BOOLEAN;
		}

		assert false : "Unreachable code";
		return null;
	}
}
