package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Negocio;
import com.app.repository.NegocioRepository;
import com.app.service.NegocioService;

@Service
public class NegocioImpl extends DaoImpl<Negocio, Integer> implements NegocioService {

	@Autowired
	private NegocioRepository negocioRepository;

	@Override
	public CrudRepository<Negocio, Integer> getDao() {
		return negocioRepository;
	}

}
