package pkb.artolver.simple;

import pkb.artolver.Resolver;

public abstract class SimpleResolver implements Resolver {
	@Override
	public abstract String resolve(String typeName);
}
