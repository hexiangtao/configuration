package io.github.enohe.common.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamHelper {

	private static final int DEFAULT_BUFFER_SIZE = 1024;

	public static byte[] readAllBytes(InputStream is) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int n = -1;
		byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
		while ((n = is.read(buf)) >= 0) {
			baos.write(buf, 0, n);
		}
		byte[] b = baos.toByteArray();
		baos.close();
		return b;
	}
}
