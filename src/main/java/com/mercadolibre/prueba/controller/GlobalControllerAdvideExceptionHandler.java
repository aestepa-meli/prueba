package com.mercadolibre.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mercadolibre.prueba.exception.ControlException;

@RestControllerAdvice
public class GlobalControllerAdvideExceptionHandler {
	
	public static final Logger LOG = LoggerFactory.getLogger(GlobalControllerAdvideExceptionHandler.class);
	
	@ExceptionHandler(ControlException.class)
	public ResponseEntity<String>error(ControlException ex){
		LOG.error(ex.getMessage(),ex);
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> error(Exception ex){
		LOG.error(ex.getMessage(),ex);
		return new ResponseEntity<>("No fue posible realizar la operacion por un error interno, contacte con el administrador",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
