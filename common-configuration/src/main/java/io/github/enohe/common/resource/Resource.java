package io.github.enohe.common.resource;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Resource {

	public static Resource from(URI uri, java.net.Proxy proxy) {
		return ResourceUtil.from(ResourceUtil.toIs(uri, proxy), uri.getPath(), Source.URI);
	}

	public static Resource from(Path path) {
		return ResourceUtil.from(ResourceUtil.toIs(path), path.toString(), Source.FILE);
	}

	public static Resource fromPath(String path) {
		return ResourceUtil.from(ResourceUtil.toIs(Paths.get(path)), path, Source.FILE);
	}

	public static Resource from(String classPath) {
		return ResourceUtil.from(ResourceUtil.toIs(classPath), classPath, Source.CLASSPATH);
	}

	public static Resource from(InputStream inputStream, String description) {
		return ResourceUtil.from(inputStream, description, Source.UNKUNOW);
	}

	public static Resource fromContent(String string) {
		return new ResourceImpl(string.getBytes(StandardCharsets.UTF_8), "", Source.CONTENT);
	}

	public static Resource fromContent(byte[] bytes) {
		return new ResourceImpl(bytes, "", Source.BINARY_CONTENT);
	}

	public String getString();

	public byte[] getBytes();

	public String getString(String charset);

	public InputStream getStream();

	enum Source {
		FILE, CLASSPATH, URI, CONTENT, BINARY_CONTENT, UNKUNOW;
	}

}
