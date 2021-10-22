package pkb.artolver;

import java.io.File;
import java.io.IOException;
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
	private String folder;

	public MainManager(Publisher publisher, String rootPath, String folder, Predicate<String> ignoreMatch) {
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
		ImportCollector collector = new ImportCollector();
		File folder = new File(rootPath);
		List<String> imports = collector.collectImportsFolderSorted(folder, ignoreMatch);
		System.out.println("Imports detected: " + imports.size());
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> result = dependencyManager.getDependencyMap(javaTypes);
		System.out.println("Dependencies: " + result.keySet().size());
		publisher.outputDependencies(result, this.folder, true);
		publisher.outputDependencies(result, this.folder, false);
		Map<String, Map<String, List<SolverJavaType>>> res2 = dependencyManager.getProjectMap(result);
		System.out.println("Projects: " + res2.keySet().size());
		publisher.outputProjects(res2, this.folder);

//		copyReportFiles(Publisher.OUTPUT + folder);
	}

	private void copyReportFiles(String targetPath) {
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("report/index.html");
		try {
			org.apache.commons.io.FileUtils.copyFile(new File("src/main/resources/report/index.html"), new File(targetPath + "index.html"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
