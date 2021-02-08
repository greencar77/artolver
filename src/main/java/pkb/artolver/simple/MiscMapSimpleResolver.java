package pkb.artolver.simple;

public class MiscMapSimpleResolver extends MapSimpleResolver {

	@Override
	protected void initMap() {
		map("ma.glasnost.orika/orika-core", "ma.glasnost.orika.");
		map("org.mockito/mockito-core", "org.mockito.");
	}
}
