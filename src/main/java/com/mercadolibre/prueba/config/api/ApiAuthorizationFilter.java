package com.mercadolibre.prueba.config.api;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mercadolibre.prueba.util.Constantes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

/**
 * <p>ApiAuthorizationFilter</p>
 * Filtro de autorizacion
 * @author Jhon
 *
 */
public class ApiAuthorizationFilter extends BasicAuthenticationFilter{
	private static final Logger LOG = LoggerFactory.getLogger(ApiAuthorizationFilter.class);
	public ApiAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	/**
	 * 
	 * @param request
	 * @returnc retorna token de autenticacion extraido de la cabecera
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String headerAuthorization = request.getHeader(Constantes.HEADER_AUTHORIZACION_KEY);
		if(StringUtils.isNotEmpty(headerAuthorization))
			return getAuthenticationToken(headerAuthorization);
		else
			return null;
	}

	/**
	 * 
	 * @param token
	 * @return valida si el token es valido para el recurso usado
	 */
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		try {
			byte[] signingKey = Constantes.JWT_SECRET.getBytes();

			Jws<Claims> parsedToken = Jwts.parser().setSigningKey(signingKey)
					.parseClaimsJws(token.replace(Constantes.TOKEN_BEARER_PREFIX, ""));

			String username = parsedToken.getBody().getSubject();

			List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody().get("rol")).stream()
					.map(authority -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());

			if (StringUtils.isNotEmpty(username)) {
				return new UsernamePasswordAuthenticationToken(username, null, authorities);
			}
		} catch (ExpiredJwtException exception) {
			LOG.warn(MessageFormat.format(Constantes.JWT_AUTHENTICATION_ERROR_TEMPLATE, "expired JWT", token,
					exception.getMessage()));
		} catch (UnsupportedJwtException exception) {
			LOG.warn(MessageFormat.format(Constantes.JWT_AUTHENTICATION_ERROR_TEMPLATE, "unsupported JWT", token,
					exception.getMessage()));
		} catch (MalformedJwtException exception) {
			LOG.warn(MessageFormat.format(Constantes.JWT_AUTHENTICATION_ERROR_TEMPLATE, "invalid JWT", token,
					exception.getMessage()));
		} catch (SignatureException exception) {
			LOG.warn(MessageFormat.format(Constantes.JWT_AUTHENTICATION_ERROR_TEMPLATE, "JWT with invalid signature", token,
					exception.getMessage()));
		} catch (IllegalArgumentException exception) {
			LOG.warn(MessageFormat.format(Constantes.JWT_AUTHENTICATION_ERROR_TEMPLATE, "empty or null JWT", token,
					exception.getMessage()));
		}

		return null;
	}






}
