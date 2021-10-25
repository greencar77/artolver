package pkb.artolver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import pkb.artolver.publisher.Publisher;

public class ReportManagerBuilder {
	private List<Publisher> publisher = new ArrayList<>();
	private String rootPath;
	private Predicate<String> ignoreMatch;
	private String code;

	public static ReportManagerBuilder start() {
		return new ReportManagerBuilder();
	}

	public ReportManager build() {
		ReportManager result = new ReportManager();

		return result;
	}
}
