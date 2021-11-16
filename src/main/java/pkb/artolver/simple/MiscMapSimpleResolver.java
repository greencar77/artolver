package pkb.artolver.simple;

public class MiscMapSimpleResolver extends MapSimpleResolver {

	@Override
	protected void initMap() {
		map("ma.glasnost.orika/orika-core", "ma.glasnost.orika.");
		map("org.mockito/mockito-core", "org.mockito.");
		map("org.reflections/reflections", "org.reflections.");
		map("org.jmockit/jmockit", "mockit.");
		map("com.squareup.retrofit2/retrofit", "retrofit2.");
		map("com.github.dozermapper/dozer-core", "com.github.dozermapper.core.");
		map("io.projectreactor/reactor-core", "reactor.util.function.");
		map("org.hamcrest/hamcrest", "org.hamcrest.");
		map("org.slf4j/slf4j-api", "org.slf4j.");
		map("com.google.guava/guava", "com.google.common.io.");
		map("com.google.guava/guava", "com.google.common.");
		map("com.google.code.gson/gson", "com.google.gson.");
		map("com.googlecode.libphonenumber/libphonenumber", "com.google.i18n.phonenumbers.");
		map("io.undertow/undertow-core", "io.undertow.server."); //and other
		map("org.jetbrains.kotlin/kotlin-stdlib", "kotlin.Pair");
		map("net.javacrumbs.json-unit/json-unit", "net.javacrumbs.jsonunit.");
		map("com.squareup.okio/okio", "okio.");
		map("org.yaml/snakeyaml", "org.yaml.snakeyaml.");
		map("org.dom4j/dom4j", "org.dom4j.");
		map("org.assertj/assertj-core", "org.assertj.core.");
		map("org.glassfish.jersey.core/jersey-server", "org.glassfish.jersey.server.");
		map("org.glassfish.hk2/hk2-api", "org.jvnet.hk2.annotations.");
		map("org.opentest4j/opentest4j", "org.opentest4j.");
		map("com.j2html/j2html", "j2html.");
		map("net.bytebuddy/byte-buddy", "net.bytebuddy.");
		map("com.hierynomus/sshj", "net.schmizz.");
		map("net.sourceforge.stripes/stripes", "net.sourceforge.stripes.");
		map("org.apache-extras.beanshell/bsh", "bsh.");
		map("com.allaire/wddx", "com.allaire.");
		map("com.auth0/java-jwt", "com.auth0.jwt.");
		map("com.computop.txms/paygate-util", "com.computop.txms.util.");
		map("com.dropbox.core/dropbox-core-sdk", "com.dropbox.core.");
		map("ch.qos.logback/logback-core", "ch.qos.logback.core.");
		map("ch.qos.logback/logback-classic", "ch.qos.logback.classic.");
		map("com.worldspan/worldspan-client", "xxclient.");
		map("com.drewnoakes/metadata-extractor", "com.drew.");
		map("wsdl4j/wsdl4j", "com.ibm.wsdl.");
		map("com.lowagie/itext", "com.lowagie.");
		map("net.sf.saxon2/saxon9-xqj", "com.saxonica.xqj.");
		map("net.sf.saxon2/saxon9-xqj", "javax.xml.xquery.");
		map("com.smartgwt/smartgwt", "com.smartgwt.");
		map("com.smartgwt/smartgwt", "com.smartgwt.");
		map("jakarta.xml.soap/jakarta.xml.soap-api", "javax.xml.soap.");
		map("com.sun.istack/istack-commons-runtime", "com.sun.istack.");

		map("org.xmlunit/xmlunit-assertj", "org.xmlunit.assertj.");
		map("org.xmlunit/xmlunit-core", "org.xmlunit."); //default

		map("commons-codec/commons-codec", "org.apache.commons.codec.");
		map("commons-collections/commons-collections", "org.apache.commons.collections.");
		map("commons-httpclient/commons-httpclient", "org.apache.commons.httpclient.");
		map("commons-io/commons-io", "org.apache.commons.io.");
		map("commons-lang/commons-lang", "org.apache.commons.lang.");

		map("org.apache.commons/commons-lang3", "org.apache.commons.lang3.");

		map("com.fasterxml.jackson.core/jackson-annotations", "com.fasterxml.jackson.annotation.");
		map("com.fasterxml.jackson.core/jackson-core", "com.fasterxml.jackson.core.");
		map("com.fasterxml.jackson.core/jackson-databind", "com.fasterxml.jackson.databind.");
		map("com.fasterxml.jackson.dataformat/jackson-dataformat-xml", "com.fasterxml.jackson.dataformat.xml.");
		map("com.fasterxml.jackson.datatype/jackson-datatype-jdk8", "com.fasterxml.jackson.datatype.jdk8.");
		map("com.fasterxml.jackson.datatype/jackson-datatype-jsr310", "com.fasterxml.jackson.datatype.jsr310.");
		map("com.fasterxml/classmate", "com.fasterxml.classmate.");

	}
}
