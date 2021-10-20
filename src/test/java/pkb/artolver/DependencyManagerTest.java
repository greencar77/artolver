package pkb.artolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import pkb.artolver.yml.ProjectYml;

class DependencyManagerTest {
	private DependencyManager manager = new DependencyManager();

	@Test
	void test() {
		Map<String, ProjectYml> result = manager.getProjectSets();
		assertEquals(14, result.size());
	}

	@Test
	void testGetProjectMap() {
		Map<String, List<SolverJavaType>> dependencyMap = new HashMap<>();
		dependencyMap.put("org.yaml/snakeyaml", List.of(new SolverJavaTypeImpl("aaa")));
		dependencyMap.put("unknown/unknown", List.of(new SolverJavaTypeImpl("bbb")));
		Map<String, Map<String, List<SolverJavaType>>> result = manager.getProjectMap(dependencyMap);
		assertEquals(1, result.size());
	}
}