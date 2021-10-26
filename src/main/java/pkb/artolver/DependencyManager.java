package pkb.artolver;

import static java.util.stream.Collectors.toMap;

import java.io.InputStream;
import java.util.ArrayList;
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

	public Map<ProjectYml, Map<String, List<SolverJavaType>>> getProjectMap(Map<String, List<SolverJavaType>> typesByDependency) {
		Map<String, ProjectYml> projectById = getProjectSets();
		Map<String, ProjectYml> projectByDependency = projectByDependency(projectById);
		Map<String, Map<String, List<SolverJavaType>>> dependenciesByProject = dependenciesByProject(typesByDependency, projectByDependency);
		return dependenciesByProject.entrySet().stream()
				.collect(toMap(e -> projectById.get(e.getKey()), Map.Entry::getValue));
	}

	private Map<String, Map<String, List<SolverJavaType>>> dependenciesByProject(Map<String, List<SolverJavaType>> typesByDependency,
			Map<String, ProjectYml> projectByDependency) {
		Map<String, Map<String, List<SolverJavaType>>> result = new HashMap<>();
		for (Map.Entry<String, List<SolverJavaType>> entry : typesByDependency.entrySet()) {
			String selectedProject = UNKNOWN;
			if (projectByDependency.containsKey(entry.getKey())) {
				selectedProject = projectByDependency.get(entry.getKey()).getId();
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

		validate(list);

		Map<String, ProjectYml> result = list.stream()
				.collect(toMap(e -> e.getId(), e -> e));

		result.put(UNKNOWN, createUnknown());
		return result;
	}

	private ProjectYml createUnknown() {
		ProjectYml result = new ProjectYml();
		result.setId(UNKNOWN);
		result.setName("UNKNOWN");
		result.setDependencies(new ArrayList<>());
		return result;
	}

	Map<String, ProjectYml> projectByDependency(Map<String, ProjectYml> projectById) {
		return projectById.values().stream()
				.flatMap(p -> p.getDependencies().stream()
						.collect(toMap(d -> d, d -> p))
						.entrySet().stream()
				)
				.collect(toMap(e -> e.getKey(), e -> e.getValue()));
	}

	private void validate(List<ProjectYml> list) {
		list.forEach(p -> {
			if (p.getId() == null) {
				throw new RuntimeException(p.getName() + " has no Id");
			}
		});
	}
}
