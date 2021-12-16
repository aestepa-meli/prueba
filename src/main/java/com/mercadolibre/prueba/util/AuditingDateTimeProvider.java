package com.mercadolibre.prueba.util;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

import org.springframework.data.auditing.DateTimeProvider;

/**
 * <p>AuditingDateTimeProvider</p>
 * Proveedor para Auditoria de Fechas
 * @author Jhon
 *
 */
public class AuditingDateTimeProvider implements DateTimeProvider{
	
	
	@Override
	public Optional<TemporalAccessor> getNow() {
		return Optional.of(LocalDateTime.now());
	}

}
