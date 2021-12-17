package com.mercadolibre.prueba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.prueba.controller.dto.UserDTO;
import com.mercadolibre.prueba.model.Usuario;
import com.mercadolibre.prueba.persistence.repositories.UsuarioRepository;
import com.mercadolibre.prueba.service.IUsuarioService;

/**
 * <p>UsuarioService</p>
 *Implementacion de IUsuarioService
 * @author Jhon
 *
 */
@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean isValidUser(String user, String password) {
		Usuario us = usuarioRepository.findByUsuario(user);
		if(us == null) {
			return false;
		}else if(us.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public void register(UserDTO userDTO) {
		Usuario u = new Usuario();
		u.setUsuario(userDTO.getUsername());
		u.setPassword(userDTO.getPassword());
		usuarioRepository.save(u);
	}

}
