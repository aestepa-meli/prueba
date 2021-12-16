package com.mercadolibre.prueba.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <p>UserDTO</p>
 * DTO para el login
 * @author Jhon
 *
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private String username;
	private String Password;
	private String token;
	  
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
