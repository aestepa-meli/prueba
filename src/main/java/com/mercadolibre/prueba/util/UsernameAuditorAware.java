package com.mercadolibre.prueba.util;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>UsernameAuditorAware</p>
 * Proveedor para Auditoria de usuario
 * @author Jhon
 *
 */
public class UsernameAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principal = (String) authentication.getPrincipal();
		return Optional.ofNullable(principal);
	}

}
