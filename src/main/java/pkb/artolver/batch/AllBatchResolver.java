package pkb.artolver.batch;

import pkb.artolver.simple.JdkSimpleResolver;
import pkb.artolver.simple.JupiterMapSimpleResolver;
import pkb.artolver.simple.MapStructResolver;
import pkb.artolver.simple.MiscMapSimpleResolver;
import pkb.artolver.simple.OkHttpResolver;
import pkb.artolver.simple.SpringResolver;

public class AllBatchResolver extends BatchResolver {

	public AllBatchResolver() {
		super();
	}

	public AllBatchResolver(boolean strict) {
		super(strict);
	}

	protected void init() {
		root
				.append(new JdkSimpleResolver())
				.append(new SpringResolver())
				.append(new OkHttpResolver())
				.append(new MapStructResolver())
				.append(new JupiterMapSimpleResolver())

				.append(new MiscMapSimpleResolver())
		;
	}
}
