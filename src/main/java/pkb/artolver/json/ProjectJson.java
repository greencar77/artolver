package pkb.artolver.json;

import java.util.List;

public class ProjectJson {
	private String name;
	private String id;
	private String projectUrl;
	private List<DependencyJson> dependencies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public List<DependencyJson> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<DependencyJson> dependencies) {
		this.dependencies = dependencies;
	}
}
