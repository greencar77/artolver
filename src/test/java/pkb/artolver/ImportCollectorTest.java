package pkb.artolver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class ImportCollectorTest {

	private ImportCollector collector = new ImportCollector();

	@Test
	void test() {
		File folder = new File("");
		List<String> imports = collector.collectImportsFolderSorted(folder);
		try {
			FileUtils.writeLines(new File("result.txt"), imports);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}