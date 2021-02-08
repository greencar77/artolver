package pkb.artolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pkb.artolver.simple.SimpleResolver;

public class ResolverNode {
	private SimpleResolver resolver;
	private boolean strict = true;
	private List<ResolverNode> children = new ArrayList<>();

	public ResolverNode() { }

	public ResolverNode(SimpleResolver resolver) {
		this.resolver = resolver;
	}

	public String resolve(String typeName) {
		Iterator<ResolverNode> it = children.iterator();
		while (it.hasNext()) {
			String result = it.next().resolve(typeName);
			if (result != null) {
				return result;
			}
		}

		if (isRoot()) {
			if (isStrict()) {
				throw new RuntimeException("Not found: " + typeName);
			} else {
				return null;
			}
		}

		return resolver.resolve(typeName);
	}

	public ResolverNode append(SimpleResolver resolver) {
		ResolverNode x = new ResolverNode(resolver);
		children.add(x);
		return this;
	}

	private boolean isRoot() {
		return resolver == null;
	}

	public boolean isStrict() {
		return strict;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}
}
