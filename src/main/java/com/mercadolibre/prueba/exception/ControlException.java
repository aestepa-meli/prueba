package com.mercadolibre.prueba.exception;

public class ControlException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ControlException(String message) {
		super(message);
	}
	
	public ControlException(String message, Throwable cause) {
		super(message,cause);
	}

}
