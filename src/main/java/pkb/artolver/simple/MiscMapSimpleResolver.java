package pkb.artolver.simple;

public class MiscMapSimpleResolver extends MapSimpleResolver {

	@Override
	protected void initMap() {
		map("ma.glasnost.orika/orika-core", "ma.glasnost.orika.");
		map("org.mockito/mockito-core", "org.mockito.");
		map("org.reflections/reflections", "org.reflections.");
		map("org.jmockit/jmockit", "mockit.");
		map("com.squareup.retrofit2/retrofit", "retrofit2.");
	}
}
