package pkb.artolver.publisher;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pkb.artolver.FileUtils;
import pkb.artolver.SolverJavaType;
import pkb.artolver.yml.ProjectYml;

public class TxtPublisher implements Publisher {

	private static final String TXT = "txt/";

	@Override
	public void outputProjects(Map<ProjectYml, Map<String, List<SolverJavaType>>> map, String folder) {
		String projects = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(projEntry -> projEntry.getKey().getName() + " " + "(" + projEntry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
		FileUtils.write(OUTPUT + folder + TXT + "projects.txt", projects);

		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(projEntry -> projEntry.getKey().getName() + " " + "(" + projEntry.getValue().size() + ")" + "\n"
						+ dep(projEntry.getValue()))
				.collect(Collectors.joining("\n"));
		FileUtils.write(OUTPUT + folder + TXT + "projects_plus.txt", result);
	}

	private String dep(Map<String, List<SolverJavaType>> input) {
		return input.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> "    " + entry.getKey() + " " + "(" + entry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
	}

	@Override
	public void outputDependencies(Map<String, List<SolverJavaType>> map, String folder, boolean compact) {
		String result;
		if (compact) {
			result = map.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getKey))
					.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")")
					.collect(Collectors.joining("\n"));
			FileUtils.write(OUTPUT + folder + TXT + "dependencies.txt", result);
		} else {
			result = map.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getKey))
					.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")" + "\n"
							+ entry.getValue().stream()
							.map(v -> "    " + v.getType())
							.collect(Collectors.joining("\n"))
					)
					.collect(Collectors.joining("\n"));
			FileUtils.write(OUTPUT + folder + TXT + "dependencies_plus.txt", result);
		}
	}

	@Override
	public void postProcess(String folder) {}
}
