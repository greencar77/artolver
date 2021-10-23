package pkb.artolver.simple;

public class SpringResolver extends SimpleResolver {
	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("org.springframework.web.servlet")) {
			return "org.springframework/spring-webmvc";
		} else if (typeName.startsWith("org.springframework.web.")
				|| typeName.startsWith("org.springframework.http.")) {
			return "org.springframework/spring-web";
		} else if (typeName.startsWith("org.springframework.boot.")) {
			return "org.springframework/spring-boot";
		} else if (typeName.startsWith("org.springframework.beans.")) {
			return "org.springframework/spring-beans";
		} else if (in(typeName, "org.springframework.context.", "org.springframework.validation")) {
			return "org.springframework/spring-context";
		} else if (in(typeName, "org.springframework.core.", "org.springframework.util")) {
			return "org.springframework/spring-core";
		} else if (typeName.startsWith("org.springframework.test.")) {
			return "org.springframework/spring-test";
		}
		return null;
	}
}
