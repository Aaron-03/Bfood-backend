package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Usuario;
import com.app.repository.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String username){
        return usuarioRepository.findByUsername(username);
    }

    public boolean existsByNombreUsuario(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    public Usuario obtenerUsuario(String rucIn) {
    	return usuarioRepository.spObtenerUser(rucIn);
    }
}
