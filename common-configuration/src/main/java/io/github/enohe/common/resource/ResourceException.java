package io.github.enohe.common.resource;

public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceException() {
		super();
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(Throwable cause) {
		super(cause);
	}
}
