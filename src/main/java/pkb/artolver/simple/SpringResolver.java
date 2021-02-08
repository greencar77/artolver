package pkb.artolver.simple;

public class SpringResolver extends SimpleResolver {
	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("org.springframework.web.servlet")) {
			return "org.springframework/spring-webmvc";
		} else if (typeName.startsWith("org.springframework.web.")) {
			return "org.springframework/spring-web";
		} else if (typeName.startsWith("org.springframework.boot.")) {
			return "org.springframework/spring-boot";
		}
		return null;
	}
}
