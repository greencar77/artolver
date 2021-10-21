package pkb.artolver;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import pkb.artolver.publisher.Publisher;

public class MainManager {
	private Publisher publisher;
	private String rootPath;
	private DependencyManager dependencyManager = new DependencyManager();
	private Predicate<String> ignoreMatch;

	public MainManager(Publisher publisher, String rootPath, Predicate<String> ignoreMatch) {
		this.publisher = publisher;
		this.rootPath = rootPath;
		this.ignoreMatch = ignoreMatch;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public void createReport() {
		ImportCollector collector = new ImportCollector();
		File folder = new File(rootPath);
		List<String> imports = collector.collectImportsFolderSorted(folder, ignoreMatch);
		System.out.println("Imports detected: " + imports.size());
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> result = dependencyManager.getDependencyMap(javaTypes);
//		publisher.outputDepList(result);
//		publisher.outputDepContainers(result);
		Map<String, Map<String, List<SolverJavaType>>> res2 = dependencyManager.getProjectMap(result);
		publisher.outputProjects(res2);
	}
}
