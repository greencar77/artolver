package pkb.artolver.simple;

public class MapStructResolver extends SimpleResolver {
	@Override
	public String resolve(String typeName) {
		if (typeName.startsWith("org.mapstruct.")) {
			return "org.mapstruct/mapstruct";
		}
		return null;
	}
}
