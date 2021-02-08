package pkb.artolver;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pkb.artolver.batch.BatchResolver;

public class MultiResolver {
	private BatchResolver batchResolver;

	public MultiResolver(BatchResolver batchResolver) {
		this.batchResolver = batchResolver;
	}

	public Map<String, List<String>> resolve(Set<String> types) {
		return types.stream()
				.collect(groupingBy(t -> wrap(batchResolver.resolve(t))));
	}

	public Map<String, List<SolverJavaType>> resolveTypes(Set<? extends SolverJavaType> types) {
		return types.stream()
				.collect(groupingBy(t -> wrap(batchResolver.resolve(t.getType()))));
	}

	private String wrap(String group) {
		return group == null? "_UNKNOWN": group;
	}
}
