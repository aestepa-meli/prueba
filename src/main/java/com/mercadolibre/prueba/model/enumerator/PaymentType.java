package com.mercadolibre.prueba.model.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>PaymentType</p>
 * Enumerador que indica el estado del pago
 * @author Jhon
 *
 */
public enum PaymentType {
	MADE,MISSED;
	
	/**
	 * Util para recibir minuscalas en el json entrante
	 * @return valor del enumerador en minusculas
	 */
	@JsonValue
	public String getValue() {
		return this.name().toLowerCase();
	}
}
