package pkb.artolver.publisher;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pkb.artolver.FileUtils;
import pkb.artolver.SolverJavaType;

public class TxtPublisher implements Publisher {

	@Override
	public void outputProjects(Map<String, Map<String, List<SolverJavaType>>> map, String folder) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(projEntry -> projEntry.getKey() + " " + "(" + projEntry.getValue().size() + ")" + "\n"
						+ dep(projEntry.getValue()))
				.collect(Collectors.joining("\n"));
		FileUtils.write(OUTPUT + folder + "projects.txt", result);
	}

	private String dep(Map<String, List<SolverJavaType>> input) {
		return input.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> "    " + entry.getKey() + " " + "(" + entry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
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
		FileUtils.write(OUTPUT + "depcontainers.txt", result);
	}

	public void outputDepList(Map<String, List<SolverJavaType>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
		FileUtils.write(OUTPUT + "deplist.txt", result);
	}
}
