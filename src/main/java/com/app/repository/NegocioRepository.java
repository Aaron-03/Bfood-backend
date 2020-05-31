package com.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Negocio;

@Repository
public interface NegocioRepository extends CrudRepository<Negocio, Integer> {
	
}
