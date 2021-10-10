package pkb.artolver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import pkb.artolver.batch.AllBatchResolver;

public class MapTest {

	@Test
	void test() {
		ImportCollector collector = new ImportCollector();
		File folder = new File("");
		List<String> imports = collector.collectImportsFolderSorted(folder);
		List<SolverJavaType> javaTypes = imports.stream()
				.map(s -> new SolverJavaTypeImpl(s))
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> result = getDependencyMap(javaTypes);
		outputDepList(result);
		output(result);
	}

	private Map<String, List<SolverJavaType>> getDependencyMap(Collection<? extends SolverJavaType> javaTypes) {
		AllBatchResolver batchResolver = new AllBatchResolver(false);
		MultiResolver multiResolver = new MultiResolver(batchResolver);
		return multiResolver.resolveTypes(javaTypes);
	}

	private void output(Map<String, List<SolverJavaType>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")" + "\n"
							 + entry.getValue().stream()
								.map(v -> "    " + v.getType())
								.collect(Collectors.joining("\n"))
				)
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File("target/dep.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void outputDepList(Map<String, List<SolverJavaType>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")"
				)
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File("target/deplist.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
