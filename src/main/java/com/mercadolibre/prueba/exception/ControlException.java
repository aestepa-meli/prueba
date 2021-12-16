package com.mercadolibre.prueba.exception;

/**
 * <p>ControlException</p>
 * Excepcion controlada
 * @author Jhon
 *
 */
public class ControlException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param mensaje de error
	 */
	public ControlException(String message) {
		super(message);
	}
	/**
	 * 
	 * @param mensaje de error
	 * @param causa que lanzo la excepcion 
	 */
	public ControlException(String message, Throwable cause) {
		super(message,cause);
	}

}
