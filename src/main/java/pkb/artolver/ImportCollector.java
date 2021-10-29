package pkb.artolver;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class ImportCollector {
	public List<String> collectImportsFolderSorted(String path, Predicate<String> ignoreMatch) {
		return collectImportsFolderSorted(new File(path), ignoreMatch);
	}

	public List<String> collectImportsFolderSorted(File folder, Predicate<String> ignoreMatch) {
		if (ignoreMatch == null) {
			return collectImportsFolderSorted(folder);
		}
		List<String> result = collectImportsFolder(folder).stream()
				.filter(s -> !ignoreMatch.test(s))
				.collect(Collectors.toList());
		Collections.sort(result);
		return result;
	}

	public List<String> collectImportsFolderSorted(File folder) {
		List<String> result = new ArrayList<>(collectImportsFolder(folder));
		Collections.sort(result);
		return result;
	}

	public Set<String> collectImportsFolder(File folder) {
		Set<String> result = new HashSet<>();

		result.addAll(Arrays.stream(folder.listFiles())
				.filter(f -> f.isFile() && f.getName().endsWith(".java"))
				.flatMap(javaFile -> collectImportsFile(javaFile).stream())
				.collect(toSet()));

		//recursion
		result.addAll(Arrays.stream(folder.listFiles())
				.filter(f -> f.isDirectory())
				.flatMap(f -> collectImportsFolder(f).stream())
				.collect(toSet()));

		return result;
	}

	private Set<String> collectImportsFile(File javaFile) {
		return readImportCandidateLines(javaFile).stream()
				.filter(l -> l.trim().matches("^import(.*)"))
				.map(importLine -> {
					String className = importLine.trim().substring("import ".length());
					className = className.substring(0, className.length() - 1); //strip off last ";"
					if (className.startsWith("static ")) {
						className = className.substring(className.indexOf(" ") + 1);
						className = className.substring(0, className.lastIndexOf("."));
					}
					return className;
				})
				.collect(toSet());
	}

	public static List<String> readImportCandidateLines(File file) {
		try {
			List<String> result = FileUtils.readLines(file);
			return getPreludePart(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 * return code lines up to the first '{' bracket (where java type definition starts, like "public class Alpha() { ..."})
	 */
	private static List<String> getPreludePart(List<String> lines) {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).indexOf('{') > -1) {
				return lines.subList(0, i);
			}
		}
		return lines;
	}
}
