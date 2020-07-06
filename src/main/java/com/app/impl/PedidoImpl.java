package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Pedido;
import com.app.repository.PedidoRepository;
import com.app.service.PedidoService;

@Service
public class PedidoImpl extends DaoImpl<Pedido, Integer> implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public CrudRepository<Pedido, Integer> getDao() {
		return pedidoRepository;
	}

	/*
	 * @Override public Pedido registrarPedido(Pedido p) {
	 * 
	 * p.getDetalles().forEach(det->det.setPedido(p));
	 * 
	 * return pedidoRepository.save(p); }
	 */

}
