package pkb.artolver.simple;

public class JdkSimpleResolver extends SimpleResolver {
	public static final String JDK = "JDK";

	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("java.")) {
			return JDK;
		} else if (typeName.startsWith("javax.")
				&& !typeName.startsWith("javax.xml.soap.")) {
			return JDK;
		} else if (typeName.startsWith("com.sun.")) {
			return JDK;
		} else if (in(typeName, "org.w3c.dom.", "org.xml.sax.")) {
			return JDK;
		}
		return null;
	}
}
