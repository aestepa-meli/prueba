package com.mercadolibre.prueba.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadolibre.prueba.model.Usuario;

/**
 * <p>UsuarioRepository</p>
 * Repositorio para validar Usuario
 * @author Jhon
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	/**
	 * 
	 * @param usuario
	 * @return retorna el usuario para su posterior validacion
	 */
	Usuario findByUsuario(String usuario);
}
