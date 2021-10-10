package pkb.artolver;

public class SolverJavaTypeImpl implements SolverJavaType {
	private String type;

	public SolverJavaTypeImpl(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}
}
