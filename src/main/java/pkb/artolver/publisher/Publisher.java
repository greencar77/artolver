package pkb.artolver.publisher;

import java.util.List;
import java.util.Map;

import pkb.artolver.SolverJavaType;

public interface Publisher {
	String OUTPUT = "target/report/";

	void outputProjects(Map<String, Map<String, List<SolverJavaType>>> map, String folder);
	void outputDependencies(Map<String, List<SolverJavaType>> map, String folder, boolean compact);
	void postProcess(String folder);
}
