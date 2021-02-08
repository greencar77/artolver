package pkb.artolver.simple;

public class JdkSimpleResolver extends SimpleResolver {
	public static final String JDK = "JDK";

	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("java.")) {
			return JDK;
		} else if (typeName.startsWith("javax.")) {
			return JDK;
		} else if (typeName.startsWith("com.sun.")) {
			return JDK;
		}
		return null;
	}
}
