package pkb.artolver.simple;

public class OkHttpResolver extends SimpleResolver {
	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("okhttp3.logging")) {
			return "com.squareup.okhttp3/logging-interceptor";
		} else if (typeName.startsWith("okhttp3.")) {
			return "com.squareup.okhttp3/okhttp";
		}
		return null;
	}
}
