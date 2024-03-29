package pkb.artolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import pkb.artolver.batch.AllBatchResolver;

class DependencyGrouperTest {
	private final DependencyGrouper dependencyGrouper;

	public DependencyGrouperTest() {
		AllBatchResolver batchResolver = new AllBatchResolver(true);
		this.dependencyGrouper = new DependencyGrouper(batchResolver);
	}

	@Test
	public void test() {
		Set<String> input = new HashSet<>();
		input.add("okhttp3.logging.HttpLoggingInterceptor");
		input.add("java.lang.Thread");
		input.add("NOTEXISTING");

		Map<String, List<String>> result = dependencyGrouper.resolve(input);
		assertEquals(3, result.keySet().size());
	}
}