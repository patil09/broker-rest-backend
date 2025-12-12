package com.radianbroker.exceptions;

public class HL7SendException extends RuntimeException  {

	private static final long serialVersionUID = 1779161774016209222L;

	public HL7SendException() {
		super();
	}

	public HL7SendException(final String message) {
		super(message);
	}
}
