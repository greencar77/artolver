package pkb.artolver.simple;

import java.util.Arrays;

import pkb.artolver.Resolver;

public abstract class SimpleResolver implements Resolver {
	@Override
	public abstract String resolve(String typeName);

	protected boolean in(String typeName, String... start) {
		return Arrays.stream(start)
				.map(st -> typeName.startsWith(st))
				.filter(r -> r)
				.findFirst().isPresent();
	}
}
