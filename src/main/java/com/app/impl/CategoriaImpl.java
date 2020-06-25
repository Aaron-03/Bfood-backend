package com.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Categoria;
import com.app.repository.CategoriaRepository;
import com.app.service.CategoriaService;

@Service
public class CategoriaImpl extends DaoImpl<Categoria, Integer> implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public CrudRepository<Categoria, Integer> getDao() {

		return categoriaRepository;
	}

}
