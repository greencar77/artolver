package pkb.artolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.google.gson.Gson;
import pkb.artolver.batch.AllBatchResolver;
import pkb.artolver.json.ProjectJson;
import pkb.artolver.simple.SimpleResolver;
import pkb.artolver.yml.ProjectYml;

public class DependencyManager {
	private static final String OUTPUT = "target/report/";

	public Map<String, List<SolverJavaType>> getDependencyMap(Collection<? extends SolverJavaType> javaTypes, SimpleResolver... additionalResolvers) {
		AllBatchResolver batchResolver = new AllBatchResolver(false);
		if (additionalResolvers != null) {
			Arrays.stream(additionalResolvers)
					.forEach(ar -> batchResolver.add(ar));
		}
		DependencyGrouper dependencyGrouper = new DependencyGrouper(batchResolver);
		return dependencyGrouper.resolveTypes(javaTypes);
	}

	public Map<String, Map<String, List<SolverJavaType>>> getProjectMap(Map<String, List<SolverJavaType>> dependencyMap) {
		Map<String, ProjectYml> depProj = getProjectSets();

		Map<String, Map<String, List<SolverJavaType>>> result = new HashMap<>();
		for (Map.Entry<String, List<SolverJavaType>> entry : dependencyMap.entrySet()) {
			String selectedProject = "_UNKNOWN";
			if (depProj.containsKey(entry.getKey())) {
				ProjectYml project = depProj.get(entry.getKey());
				selectedProject = project.getName();
			}

			if (!result.containsKey(selectedProject)) {
				result.put(selectedProject, new HashMap<>());
			}
			result.get(selectedProject).put(entry.getKey(), entry.getValue());
		}

		return result;
	}

	Map<String, ProjectYml> getProjectSets() {
		Yaml yaml = new Yaml(new Constructor(ProjectYml.class));
		InputStream inputStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream("projects.yml");
		List<ProjectYml> list = StreamSupport.stream(yaml.loadAll(inputStream).spliterator(), false)
				.map(x -> (ProjectYml) x)
				.collect(Collectors.toList());

		return list.stream()
				.flatMap(p -> p.getDependencies().stream()
						.collect(Collectors.toMap(d -> d, d -> p))
						.entrySet().stream()
				)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
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
				.map(entry -> entry.getKey() + " " + "(" + entry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File(OUTPUT + "deplist.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void outputProjList(Map<String, Map<String, List<SolverJavaType>>> map) {
		String result = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(projEntry -> projEntry.getKey() + " " + "(" + projEntry.getValue().size() + ")" + "\n"
					+ dep(projEntry.getValue()))
				.collect(Collectors.joining("\n"));
		try {
			FileUtils.write(new File(OUTPUT + "projlist.txt"), result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String dep(Map<String, List<SolverJavaType>> input) {
		return input.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(entry -> "    " + entry.getKey() + " " + "(" + entry.getValue().size() + ")")
				.collect(Collectors.joining("\n"));
	}

	public String toJson(Map<String, Map<String, List<SolverJavaType>>> map) {
		List<ProjectJson> json = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> {
					ProjectJson result = new ProjectJson();
					result.setName(e.getKey());
					return result;
				})
				.collect(Collectors.toList());

		Gson gson = new Gson();
		return gson.toJson(json);
	}
}
