package pkb.artolver;

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
		Map<String, List<SolverJavaType>> result = dependencyManager.getDependencyMap(javaTypes);
		System.out.println("Dependencies: " + result.keySet().size());
		if (result.containsKey("_UNKNOWN")) {
			System.out.println("Orphan classes: " + result.get("_UNKNOWN").size());
		}
		publisher.outputDependencies(result, this.folder, true);
		publisher.outputDependencies(result, this.folder, false);
		Map<String, Map<String, List<SolverJavaType>>> res2 = dependencyManager.getProjectMap(result);
		if (res2.containsKey("_UNKNOWN")) {
			System.out.println("Orphan dependencies: " + res2.get("_UNKNOWN").size());
		}
		System.out.println("Projects: " + res2.keySet().size());
		publisher.outputProjects(res2, this.folder);
		publisher.postProcess(this.folder);
	}
}
