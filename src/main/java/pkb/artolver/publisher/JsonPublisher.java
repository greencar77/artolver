package pkb.artolver.publisher;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import pkb.artolver.FileUtils;
import pkb.artolver.SolverJavaType;
import pkb.artolver.json.ProjectJson;

public class JsonPublisher implements Publisher {

	@Override
	public void outputProjects(Map<String, Map<String, List<SolverJavaType>>> map, String folder) {
		List<ProjectJson> json = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> {
					ProjectJson result = new ProjectJson();
					result.setName(e.getKey());
					return result;
				})
				.collect(Collectors.toList());

		Gson gson = new Gson();
		FileUtils.write(OUTPUT + folder + "projects.js", "const proj = " + gson.toJson(json) + ";");
	}
}
