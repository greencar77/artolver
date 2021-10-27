package pkb.artolver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import pkb.artolver.publisher.Publisher;

public class ReportManagerBuilder {
	private List<Publisher> publishers = new ArrayList<>();
	private String rootPath;
	private Predicate<String> ignoreMatcher;
	private String code;
	private String title;

	public static ReportManagerBuilder start() {
		return new ReportManagerBuilder();
	}

	public ReportManagerBuilder withRootPath(String rootPath) {
		this.rootPath = rootPath;
		return this;
	}

	public ReportManagerBuilder withIgnoreMatcher(Predicate<String> ignoreMatcher) {
		this.ignoreMatcher = ignoreMatcher;
		return this;
	}

	public ReportManagerBuilder withName(String code, String title) {
		this.code = code;
		this.title = title;
		return this;
	}

	public ReportManagerBuilder withPublisher(Publisher publisher) {
		this.publishers.add(publisher);
		return this;
	}

	public ReportManager build() {
		ReportManager result = new ReportManager();
		result.setPublishers(publishers);
		result.setIgnoreMatcher(ignoreMatcher);
		result.setRootPath(rootPath);
		result.setFolder(code + (code.endsWith("/") ? "" : "/"));
		result.setTitle(title);
		return result;
	}
}
