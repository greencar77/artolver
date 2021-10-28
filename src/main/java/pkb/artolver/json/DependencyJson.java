package pkb.artolver.json;

import java.util.List;

public class DependencyJson {
	private String name;
	private int typesCount;
	private List<String> types;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTypesCount() {
		return typesCount;
	}

	public void setTypesCount(int typesCount) {
		this.typesCount = typesCount;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
