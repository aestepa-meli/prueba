package com.mercadolibre.prueba.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.mercadolibre.prueba.service.IUsuarioService;
/**
 * <p>UserDetailsAuthenticationProvider</p>
 * Proveedor de autenticacion
 * @author Jhon
 *
 */
@Component
public class UserDetailsAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private IUsuarioService usuarioService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String principal = (String) authentication.getPrincipal();
		final String password  = (String) authentication.getCredentials();
		return createAuthentication(principal, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}


	protected Authentication createAuthentication(String principal, String password) {
		boolean isValid = usuarioService.isValidUser(principal, password);
		
		if(!isValid) {
			throw new BadCredentialsException("User is not valid");
		}
		return new UsernamePasswordAuthenticationToken(principal, password);
	}

}
