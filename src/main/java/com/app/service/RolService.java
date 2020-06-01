package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Rol;
import com.app.repository.RolRepository;



@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
