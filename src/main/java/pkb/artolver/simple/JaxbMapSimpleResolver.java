package pkb.artolver.simple;

public class JaxbMapSimpleResolver extends MapSimpleResolver {
	@Override
	protected void initMap() {
		map("javax.xml.bind/jaxb-api", "javax.xml.bind.");
	}
}
