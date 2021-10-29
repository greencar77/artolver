package pkb.artolver.publisher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pkb.artolver.FileUtils;
import pkb.artolver.SolverJavaType;
import pkb.artolver.json.DependencyJson;
import pkb.artolver.json.MainJson;
import pkb.artolver.json.ProjectJson;
import pkb.artolver.yml.ProjectYml;

public class JsonPublisher implements Publisher {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public void outputProjects(Map<ProjectYml, Map<String, List<SolverJavaType>>> map, String folder) {
		List<ProjectJson> json = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> map(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		FileUtils.write(OUTPUT + folder + "data/" + "projects.js", "const proj = " + GSON.toJson(json) + ";");
	}

	@Override
	public void outputDependencies(Map<String, List<SolverJavaType>> map, String folder) {
		List<DependencyJson> json = map.entrySet().stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> map(e, false))
				.collect(Collectors.toList());
		FileUtils.write(OUTPUT + folder + "data/" + "dependencies.js", "const dependencies = " + GSON.toJson(json) + ";");
	}

	@Override
	public void outputMain(String title, int typeCount, int dependencyCount, int projectCount, String folder) {
		MainJson json = new MainJson();
		json.setTitle(title);
		json.setTypeCount(typeCount);
		json.setDependencyCount(dependencyCount);
		json.setProjectCount(projectCount);
		FileUtils.write(OUTPUT + folder + "data/" + "main.js", "const main = " + GSON.toJson(json) + ";");
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

	private ProjectJson map(ProjectYml project, Map<String, List<SolverJavaType>> actualDependencies) {
		ProjectJson result = new ProjectJson();
		result.setId(project.getId());
		result.setName(project.getName());
		result.setProjectUrl(project.getUrl());
		result.setDependencies(actualDependencies.entrySet()
				.stream()
				.sorted(Comparator.comparing(Map.Entry::getKey))
				.map(e -> map(e, true))
				.collect(Collectors.toList()));
		return result;
	}

	private DependencyJson map(Map.Entry<String, List<SolverJavaType>> entry, boolean compact) {
		DependencyJson result = new DependencyJson();
		result.setName(entry.getKey());
		result.setTypesCount(entry.getValue().size());
		if (!compact) {
			result.setTypes(entry.getValue().stream()
					.map(v -> v.getType())
					.collect(Collectors.toList())
			);
		}
		return result;
	}
}
