package com.mercadolibre.prueba.service;

import com.mercadolibre.prueba.controller.dto.UserDTO;

/**
 * <p>IUsuarioService</p>
 * Interfaz de servicio para el manejo de usuarios
 * @author Jhon
 *
 */
public interface IUsuarioService {
	/**
	 * 
	 * @param user
	 * @param password
	 * @return valor booleando indicando si el usuario es valido
	 */
	boolean isValidUser(String user, String password);
	
	/**
	 * Registro de usuarios
	 * @param userDTO
	 */
	void register(UserDTO userDTO);
}
