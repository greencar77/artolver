package pkb.artolver.yml;

import java.util.List;
import java.util.Objects;

public class ProjectYml {
	private String name;
	private String id;
	private List<String> dependencies;

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

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProjectYml that = (ProjectYml) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
