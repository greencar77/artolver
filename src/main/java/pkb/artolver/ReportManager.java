package pkb.artolver;

import static pkb.artolver.DependencyManager.UNKNOWN;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pkb.artolver.publisher.Publisher;
import pkb.artolver.yml.ProjectYml;

public class ReportManager {
	private DependencyManager dependencyManager = new DependencyManager();
	private ImportCollector collector = new ImportCollector();

	private List<Publisher> publishers;
	private String rootPath;
	private Predicate<String> ignoreMatcher;
	private String folder;
	private String title;

	ReportManager() {}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void setIgnoreMatcher(Predicate<String> ignoreMatcher) {
		this.ignoreMatcher = ignoreMatcher;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void createReport() {
		List<String> imports = collector.collectImportsFolderSorted(rootPath, ignoreMatcher);
		System.out.println("Imports detected: " + imports.size());
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> dependencyMap = dependencyManager.getDependencyMap(javaTypes);
		System.out.println("Dependencies: " + dependencyMap.keySet().size());
		if (dependencyMap.containsKey(UNKNOWN)) {
			System.out.println("Orphan classes: " + dependencyMap.get(UNKNOWN).size());
		}
		Map<ProjectYml, Map<String, List<SolverJavaType>>> projectMap = dependencyManager.getProjectMap(dependencyMap);
		//		if (projectMap.containsKey(UNKNOWN)) {
		//			System.out.println("Orphan dependencies: " + projectMap.get(UNKNOWN).size());
		//		}
		System.out.println("Projects: " + projectMap.keySet().size());

		publishers.forEach(p -> {
			p.outputDependencies(dependencyMap, folder, true);
			p.outputDependencies(dependencyMap, folder, false);
			p.outputProjects(projectMap, folder);
			p.postProcess(folder);
		});
	}
}
