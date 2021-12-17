package com.mercadolibre.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.prueba.controller.dto.UserDTO;
import com.mercadolibre.prueba.exception.ControlException;
import com.mercadolibre.prueba.service.IUsuarioService;
/**
 * <p>UserController</p>
 * Modulo de ayuda para crear usuarios
 * @author Jhon
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * 
	 * @param dto
	 * @return Mensaje indicando el registro del usuario
	 * @throws ControlException
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerLoanApplication(@RequestBody UserDTO dto) throws ControlException {
		LOG.info("Register User ");
		usuarioService.register(dto);
		return new ResponseEntity<>("Saved", HttpStatus.OK);
	}

}
