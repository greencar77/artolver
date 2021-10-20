package pkb.artolver;

import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class IdGen {
	@Test
	void gen() {
		IntStream.range(0, 10)
				.forEach(i -> System.out.println(RandomStringUtils.randomAlphanumeric(8).toUpperCase()));
	}
}
