package com.app.service;

import org.springframework.stereotype.Service;

import com.app.common.Dao;
import com.app.entity.Categoria;

@Service
public interface CategoriaService extends Dao<Categoria, Integer> {

}
