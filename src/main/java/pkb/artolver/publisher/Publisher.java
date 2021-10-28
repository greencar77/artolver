package pkb.artolver.publisher;

import java.util.List;
import java.util.Map;

import pkb.artolver.SolverJavaType;
import pkb.artolver.yml.ProjectYml;

public interface Publisher {
	String OUTPUT = "target/report/";

	void outputProjects(Map<ProjectYml, Map<String, List<SolverJavaType>>> map, String folder);
	void outputDependencies(Map<String, List<SolverJavaType>> map, String folder);
	void postProcess(String folder);
}
