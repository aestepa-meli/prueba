package com.mercadolibre.prueba.config.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.prueba.controller.dto.UserDTO;
import com.mercadolibre.prueba.util.Constantes;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
/**
 * <p>ApiAuthenticationFilter</p>
 * 
 * Filtro de autenticacion
 * @author Jhon
 *
 */
public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	public ApiAuthenticationFilter(AuthenticationManager authenticationManager,String path) {
		this.authenticationManager = authenticationManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(path,"POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserDTO user = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException("Authentication Error", e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException {
		List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
		
		String token = Jwts.builder().setIssuedAt(new Date())
							.signWith(Keys.hmacShaKeyFor(Constantes.JWT_SECRET.getBytes()), SignatureAlgorithm.HS512)
							.setHeaderParam("typ", Constantes.TOKEN_TYPE)
							.setSubject(auth.getName())
							.setExpiration(new Date(System.currentTimeMillis() + Constantes.TOKEN_EXPIRATION_TIME))
							.claim("rol", roles)
							.compact();
		
		UserDTO user = new UserDTO();
		user.setUsername(auth.getName());
		user.setToken(token);
		
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		PrintWriter writer = response.getWriter();
		writer.write(new ObjectMapper().writeValueAsString(user));
		writer.flush();
	}



}
