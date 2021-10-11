package pkb.artolver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

import pkb.artolver.batch.AllBatchResolver;

public class DependencyManager {
	private static final String OUTPUT = "target/report/";

	public Map<String, List<SolverJavaType>> getDependencyMap(Collection<? extends SolverJavaType> javaTypes) {
		AllBatchResolver batchResolver = new AllBatchResolver(false);
		MultiResolver multiResolver = new MultiResolver(batchResolver);
		return multiResolver.resolveTypes(javaTypes);
	}

	public void outputDepContainers(Map<String, List<SolverJavaType>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")" + "\n"
						+ entry.getValue().stream()
						.map(v -> "    " + v.getType())
						.collect(Collectors.joining("\n"))
				)
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File(OUTPUT + "depcontainers.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void outputDepList(Map<String, List<SolverJavaType>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")"
				)
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File(OUTPUT + "deplist.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
