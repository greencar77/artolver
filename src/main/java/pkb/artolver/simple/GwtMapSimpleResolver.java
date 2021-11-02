package pkb.artolver.simple;

public class GwtMapSimpleResolver extends MapSimpleResolver {
	@Override
	protected void initMap() {
		map("com.google.gwt/gwt-user", "com.google.gwt.");
		map("com.google.gwt/gwt-user", "com.google.web.bindery.");
	}
}
