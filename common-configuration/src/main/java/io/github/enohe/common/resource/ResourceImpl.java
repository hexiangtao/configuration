package io.github.enohe.common.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ResourceImpl implements Resource {

	private String location;
	private InputStream is;
	private Source source;

	private boolean streameObtained;
	private byte[] cachedBytes;

	public ResourceImpl(byte[] bytes, String description, Source source) {

		this.cachedBytes = bytes;
		this.is = new ByteArrayInputStream(bytes);
		this.source = source;
		this.location = description;
		this.streameObtained = true;

	}

	public ResourceImpl(InputStream inputStream, String from, Source source) {
		this.location = from;
		this.is = inputStream;
		this.source = source;
	}

	@Override
	public String getString() {
		check();
		cachedBytes();
		return new String(cachedBytes, StandardCharsets.UTF_8);
	}

	@Override
	public InputStream getStream() {
		check();
		streameObtained = true;
		if (cachedBytes == null) {
			return this.is;
		}
		return new ByteArrayInputStream(cachedBytes);
	}

	public String getLocation() {
		return location;
	}

	public Source getSource() {
		return source;
	}

	@Override
	public String getString(String charset) {
		check();
		cachedBytes();
		return new String(cachedBytes, Charset.forName(charset));
	}

	private void cachedBytes() {

		if (cachedBytes != null) {
			return;
		}
		try {
			cachedBytes = StreamHelper.readAllBytes(is);
			streameObtained = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void check() {
		if (streameObtained && cachedBytes == null) {
			throw new ResourceException("stream had geted");
		}
	}

	@Override
	public byte[] getBytes() {
		check();
		cachedBytes();
		return cachedBytes;
	}

}
