package pkb.artolver;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class MapTest {

	private DependencyManager dependencyManager = new DependencyManager();

	@Test
	void test() {
		ImportCollector collector = new ImportCollector();
		File folder = new File("");
		List<String> imports = collector.collectImportsFolderSorted(folder);
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> result = dependencyManager.getDependencyMap(javaTypes);
		dependencyManager.outputDepList(result);
		dependencyManager.outputDepContainers(result);
	}

	@Test
	void testProj() {
		ImportCollector collector = new ImportCollector();
		File folder = new File("");
		List<String> imports = collector.collectImportsFolderSorted(folder);
		List<SolverJavaType> javaTypes = imports.stream()
				.map(SolverJavaTypeImpl::new)
				.collect(Collectors.toList());
		Map<String, List<SolverJavaType>> result = dependencyManager.getDependencyMap(javaTypes);
		Map<String, Map<String, List<SolverJavaType>>> res2 = dependencyManager.getProjectMap(result);
		dependencyManager.outputProjList(res2);
	}
}
