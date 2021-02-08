package pkb.artolver.simple;

import java.util.ArrayList;
import java.util.List;

public abstract class MapSimpleResolver extends SimpleResolver {
	private static final String SEPARATOR = "\t";

	protected List<String> list = new ArrayList<>();

	protected abstract void initMap();

	public MapSimpleResolver() {
		initMap();
	}

	protected void map(String dependency, String start) {
		list.add(start + SEPARATOR + dependency);
	}

	@Override
	public String resolve(String typeName) {
		for (String entry: list) {
			String[] parts = entry.split(SEPARATOR);
			String key = parts[0];
			String value = parts[1];
			if (typeName.startsWith(key)) {
				return value;
			}
		}
		return null;
	}
}
