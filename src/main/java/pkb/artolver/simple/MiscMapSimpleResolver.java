package pkb.artolver.simple;

public class MiscMapSimpleResolver extends MapSimpleResolver {

	@Override
	protected void initMap() {
		map("ma.glasnost.orika/orika-core", "ma.glasnost.orika.");
		map("org.mockito/mockito-core", "org.mockito.");
		map("org.reflections/reflections", "org.reflections.");
		map("org.jmockit/jmockit", "mockit.");
		map("com.squareup.retrofit2/retrofit", "retrofit2.");
		map("com.fasterxml.jackson.core/jackson-annotations", "com.fasterxml.jackson.annotation.");
		map("com.fasterxml.jackson.core/jackson-core", "com.fasterxml.jackson.core.");
		map("com.fasterxml.jackson.core/jackson-databind", "com.fasterxml.jackson.databind.");
		map("com.github.dozermapper/dozer-core", "com.github.dozermapper.core.");
		map("io.projectreactor/reactor-core", "reactor.util.function.");
		map("org.hamcrest/hamcrest", "org.hamcrest.");
		map("org.slf4j/slf4j-api", "org.slf4j.");
	}
}
