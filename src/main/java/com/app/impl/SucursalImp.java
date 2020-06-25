package com.app.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Sucursal;
import com.app.repository.SucursalRepository;
import com.app.service.SucursalService;

@Service
public class SucursalImp extends DaoImpl<Sucursal, Integer> implements SucursalService {

	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public CrudRepository<Sucursal, Integer> getDao() {
		return sucursalRepository;
	}

	

	
}
