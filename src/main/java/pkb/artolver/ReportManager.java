package pkb.artolver;

import static pkb.artolver.DependencyManager.UNKNOWN;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pkb.artolver.publisher.Publisher;

public class ReportManager {
	private DependencyManager dependencyManager = new DependencyManager();
	private ImportCollector collector = new ImportCollector();

	private Publisher publisher;
	private String rootPath;
	private Predicate<String> ignoreMatch;
	private String folder;

	public ReportManager() {}

	public ReportManager(Publisher publisher, String rootPath, String folder, Predicate<String> ignoreMatch) {
		this.publisher = publisher;
		this.rootPath = rootPath;
		this.folder = folder;
		this.ignoreMatch = ignoreMatch;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void createReport() {
		List<String> imports = collector.collectImportsFolderSorted(rootPath, ignoreMatch);
		System.out.println("Imports detected: " + imports.size());
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> dependencyMap = dependencyManager.getDependencyMap(javaTypes);
		System.out.println("Dependencies: " + dependencyMap.keySet().size());
		if (dependencyMap.containsKey(UNKNOWN)) {
			System.out.println("Orphan classes: " + dependencyMap.get(UNKNOWN).size());
		}
		publisher.outputDependencies(dependencyMap, folder, true);
		publisher.outputDependencies(dependencyMap, folder, false);
		Map<String, Map<String, List<SolverJavaType>>> projectMap = dependencyManager.getProjectMap(dependencyMap);
		if (projectMap.containsKey(UNKNOWN)) {
			System.out.println("Orphan dependencies: " + projectMap.get(UNKNOWN).size());
		}
		System.out.println("Projects: " + projectMap.keySet().size());
		publisher.outputProjects(projectMap, folder);
		publisher.postProcess(folder);
	}
}
