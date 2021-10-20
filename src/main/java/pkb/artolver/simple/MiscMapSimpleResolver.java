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
		map("com.google.guava/guava", "com.google.common.io.");
		map("com.google.code.gson/gson", "com.google.gson.");
		map("com.googlecode.libphonenumber/libphonenumber", "com.google.i18n.phonenumbers.");
		map("io.undertow/undertow-core", "io.undertow.server."); //and other
		map("org.jetbrains.kotlin/kotlin-stdlib", "kotlin.Pair");
		map("net.javacrumbs.json-unit/json-unit", "net.javacrumbs.jsonunit.");
		map("com.squareup.okio/okio", "okio.");
		map("org.yaml/snakeyaml", "org.yaml.snakeyaml.");

		map("commons-codec/commons-codec", "org.apache.commons.codec.");
		map("commons-collections/commons-collections", "org.apache.commons.collections.");
		map("commons-httpclient/commons-httpclient", "org.apache.commons.httpclient.");
		map("commons-io/commons-io", "org.apache.commons.io.");
		map("commons-lang/commons-lang", "org.apache.commons.lang.");
	}
}
