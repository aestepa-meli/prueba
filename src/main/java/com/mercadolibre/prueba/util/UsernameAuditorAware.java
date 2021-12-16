package com.mercadolibre.prueba.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * <p>UsernameAuditorAware</p>
 * Proveedor para Auditoria de usuario
 * @author Jhon
 *
 */
public class UsernameAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable("anonymous");
	}

}
