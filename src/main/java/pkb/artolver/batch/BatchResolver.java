package pkb.artolver.batch;

import pkb.artolver.Resolver;
import pkb.artolver.ResolverNode;

public abstract class BatchResolver implements Resolver {

	protected ResolverNode root = new ResolverNode();

	public BatchResolver() {
		this(true);
	}

	public BatchResolver(boolean strict) {
		root.setStrict(strict);
		init();
	}

	protected abstract void init();

	@Override
	public String resolve(String typeName) {
		return root.resolve(typeName);
	}
}
