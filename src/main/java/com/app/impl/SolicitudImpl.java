package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Solicitud;
import com.app.repository.SolicitudRepository;
import com.app.service.SolicitudService;

@Service
public class SolicitudImpl extends DaoImpl<Solicitud, Integer> implements SolicitudService {

	@Autowired
	private SolicitudRepository solicitudRepository;

	@Override
	public CrudRepository<Solicitud, Integer> getDao() {
		return solicitudRepository;
	}

}
