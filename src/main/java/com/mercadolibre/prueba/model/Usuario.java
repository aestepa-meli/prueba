package com.mercadolibre.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Usuario</p>
 * Entidad para manejo de usuarios
 * @author Jhon
 *
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@Column(name = "ID",updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="USUARIO",nullable = false, unique = true)
	private String usuario;
	@Column(name="password",nullable = false)
	private String password;
	@Column(name="valid",nullable = false,columnDefinition = "boolean default true")
	private boolean valid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
}
