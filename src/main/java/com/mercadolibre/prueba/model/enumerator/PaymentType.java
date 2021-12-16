package com.mercadolibre.prueba.model.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
	MADE,MISSED;
	
	
	@JsonValue
	public String getValue() {
		return this.name().toLowerCase();
	}
}
