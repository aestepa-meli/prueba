package com.mercadolibre.prueba.service;

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
}
