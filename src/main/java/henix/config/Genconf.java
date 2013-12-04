package henix.config;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class Genconf {

	public static class ConfSpec {

		public final String res;
		public final String target;
		public final Object[] params;

		public ConfSpec(String res, String target, Object[] params) {
			this.res = res;
			this.target = target;
			this.params = params;
		}
	}

	public static ConfSpec confspec(String res, String target, Object[] params) {
		return new ConfSpec(res, target, params);
	}

	public static void genconf(Class<?> clazz, List<ConfSpec> confSpecs) throws IOException {
		for (final ConfSpec spec : confSpecs) {
			final InputStream in = clazz.getResourceAsStream(spec.res);
			if (in == null) {
				throw new FileNotFoundException(spec.res);
			}
			final MessageFormat format = new MessageFormat(IOUtils.toString(in, Charsets.UTF_8));
			IOUtils.closeQuietly(in);
			System.out.print("Writing to " + spec.target + " ... ");
			FileUtils.write(new File(spec.target), format.format(spec.params), Charsets.UTF_8);
			System.out.println("done");
		}
	}
}
