package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.Usuario;
import com.app.repository.UsuarioRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		UserDetailsImpl userDetailsImpl = null;
		if (usuario != null) {
			userDetailsImpl = new UserDetailsImpl();
			userDetailsImpl.setUsuario(usuario);
		} else {
			throw new UsernameNotFoundException(":( No se encontrado el usuario: " + username);
		}
		return userDetailsImpl;
	}

}