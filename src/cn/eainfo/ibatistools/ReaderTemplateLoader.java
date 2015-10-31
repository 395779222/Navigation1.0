package cn.eainfo.ibatistools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import freemarker.cache.TemplateLoader;

public class ReaderTemplateLoader implements TemplateLoader {

	private BufferedReader bufferedReader;

	public ReaderTemplateLoader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public void closeTemplateSource(Object templateSource) throws IOException {
		((BufferedReader) templateSource).close();
	}

	public Object findTemplateSource(String name) throws IOException {
		return bufferedReader;
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return (Reader) templateSource;
	}

}
