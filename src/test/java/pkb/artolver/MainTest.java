package pkb.artolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import pkb.artolver.batch.AllBatchResolver;
import pkb.artolver.batch.BatchResolver;

public class MainTest {

	@Test
	public void test() {
		BatchResolver resolver = new AllBatchResolver();

		assertEquals("JDK", resolver.resolve("java.util.Iterator"));
		assertEquals("org.mapstruct/mapstruct", resolver.resolve("org.mapstruct.Mapper"));
		assertEquals("org.junit.jupiter/junit-jupiter-api", resolver.resolve("org.junit.jupiter.api.Assertions"));
		assertEquals("ma.glasnost.orika/orika-core", resolver.resolve("ma.glasnost.orika.MapperFacade"));
	}

	@Test
	@Disabled
	public void testBatch() {
		List<String> types;
		try {
			types = FileUtils.readLines(new File("src/test/resources/javaTypes.txt"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		BatchResolver resolver = new AllBatchResolver();
		types.forEach(t -> resolver.resolve(t));
	}
}
