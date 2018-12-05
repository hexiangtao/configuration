package io.github.enohe.common.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import io.github.enohe.common.resource.Resource.Source;

public class ResourceUtil {

	static Resource from(InputStream inputStream, String from, Source source) {

		return new ResourceImpl(inputStream, from, source);

	}

	static InputStream toIs(String classPath) {
		Objects.requireNonNull(classPath, "classPath should not be null");
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(classPath);
		Objects.requireNonNull(is, "the file" + classPath + " not exists");
		return is;
	}

	static InputStream toIs(Path path) {
		Objects.requireNonNull(path, "resource file system on path" + path.getFileName() + "does not exist");
		try {
			return Files.newInputStream(path);
		} catch (IOException e) {
			throw new ResourceException(e);
		}

	}

	static InputStream toIs(URI uri) {
		Objects.requireNonNull(uri, "uri " + uri + " should not be null");
		try {
			return uri.toURL().openStream();
		} catch (IOException e) {
			throw new ResourceException(e);
		}
	}

	static InputStream toIs(URI uri, Proxy proxy) {
		try {
			return uri.toURL().openConnection(proxy).getInputStream();
		} catch (IOException e) {
			throw new ResourceException(e);
		}
	}

}
