package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.app.common.DaoImpl;
import com.app.entity.DetallePedido;
import com.app.repository.DetallePedidoRepository;
import com.app.service.DetallePedidoService;

public class DetallePedidoImpl extends DaoImpl<DetallePedido, Integer> implements DetallePedidoService {

	@Autowired
	public DetallePedidoRepository detalle;
	@Override
	public CrudRepository<DetallePedido, Integer> getDao() {
		// TODO Auto-generated method stub
		return detalle;
	}

}
