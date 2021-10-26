package pkb.artolver.publisher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pkb.artolver.FileUtils;
import pkb.artolver.SolverJavaType;
import pkb.artolver.json.ProjectJson;
import pkb.artolver.yml.ProjectYml;

public class JsonPublisher implements Publisher {

	@Override
	public void outputProjects(Map<ProjectYml, Map<String, List<SolverJavaType>>> map, String folder) {
		List<ProjectJson> json = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> map(e.getKey()))
				.collect(Collectors.toList());

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileUtils.write(OUTPUT + folder + "data/" + "projects.js", "const proj = " + gson.toJson(json) + ";");
	}

	@Override
	public void outputDependencies(Map<String, List<SolverJavaType>> map, String folder, boolean compact) {

	}

	@Override
	public void postProcess(String folder) {
		copyReportFiles("report/main.css", Publisher.OUTPUT + folder + "main.css");
		copyReportFiles("report/index.html", Publisher.OUTPUT + folder + "index.html");
		copyReportFiles("report/main.js", Publisher.OUTPUT + folder + "main.js");
		copyReportFiles("report/project.html", Publisher.OUTPUT + folder + "project.html");
		copyReportFiles("report/project.js", Publisher.OUTPUT + folder + "project.js");
	}

	private void copyReportFiles(String sourcePath, String targetPath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(sourcePath);
		try {
			byte[] bytes = IOUtils.toByteArray(is);
			org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(targetPath), bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ProjectJson map(ProjectYml project) {
		ProjectJson result = new ProjectJson();
		result.setId(project.getId());
		result.setName(project.getName());
		result.setDependencies(project.getDependencies());
		Collections.sort(result.getDependencies());
		return result;
	}
}
