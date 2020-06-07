package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Producto;
import com.app.repository.ProductoRepository;
import com.app.service.ProductoService;




@Service
public class ProductoImpl extends DaoImpl<Producto, Integer> implements ProductoService {

	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public CrudRepository<Producto, Integer> getDao() {
		// TODO Auto-generated method stub
		return productoRepository;
	}
	
}
