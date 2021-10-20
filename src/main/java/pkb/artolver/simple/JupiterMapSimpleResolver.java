package pkb.artolver.simple;

public class JupiterMapSimpleResolver extends MapSimpleResolver {
	@Override
	protected void initMap() {
		map("org.junit.jupiter/junit-jupiter-api", "org.junit.jupiter.api.");
		map("org.junit.jupiter/junit-jupiter-params", "org.junit.jupiter.params.");
	}
}
