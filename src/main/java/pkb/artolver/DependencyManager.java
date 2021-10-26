package pkb.artolver;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import pkb.artolver.batch.AllBatchResolver;
import pkb.artolver.simple.SimpleResolver;
import pkb.artolver.yml.ProjectYml;

public class DependencyManager {

	public static final String UNKNOWN = "_UNKNOWN";

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
			String selectedProject = UNKNOWN;
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

		list.forEach(p -> {
			if (p.getId() == null) {
				throw new RuntimeException(p.getName() + " has no Id");
			}
		});

		return list.stream()
				.flatMap(p -> p.getDependencies().stream()
						.collect(Collectors.toMap(d -> d, d -> p))
						.entrySet().stream()
				)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
	}
}
