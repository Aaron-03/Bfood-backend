package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Consumidor;
import com.app.repository.ConsumidorRepository;
import com.app.service.ConsumidorService;

@Service
public class ConsumidorImpl extends DaoImpl<Consumidor, Integer> implements ConsumidorService {

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Override
	public CrudRepository<Consumidor, Integer> getDao() {
		return consumidorRepository;
	}

}
