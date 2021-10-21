package pkb.artolver;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	public static void write(String file, String string) {
		write(new File(file), string);
	}

	public static void write(File file, String string) {
		try {
			org.apache.commons.io.FileUtils.write(file, string);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
