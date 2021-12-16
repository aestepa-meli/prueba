package com.mercadolibre.prueba.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * <p>RestAuthenticationEntrtPoint</p>
 * Esta clase fuinciona como exceptionHandler de la autenticacion
 * @author Jhon
 *
 */
@Component
public class RestAuthenticationEntrtPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
	
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		ResponseEntity<String> resp = new ResponseEntity<>("No tienes Autorizacion",HttpStatus.UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.write(new ObjectMapper().writeValueAsString(resp));
		writer.flush();
	}

}
