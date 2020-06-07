package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.app.common.DaoImpl;
import com.app.entity.Producto;
import com.app.repository.ProductoRepository;
import com.app.service.ProductoService;

public class Productolmpl extends DaoImpl<Producto, Integer> implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public CrudRepository<Producto, Integer> getDao() {
		return productoRepository;
	}	

}