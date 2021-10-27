package pkb.artolver.simple;

public class JunitMapSimpleResolver extends MapSimpleResolver {
	@Override
	protected void initMap() {
		map("org.junit.jupiter/junit-jupiter-api", "org.junit.jupiter.api.");
		map("org.junit.jupiter/junit-jupiter-params", "org.junit.jupiter.params.");
		map("org.junit-pioneer/junit-pioneer", "org.junitpioneer.");
		map("junit/junit", "org.junit.");
		map("junit/junit", "junit.");
	}
}
