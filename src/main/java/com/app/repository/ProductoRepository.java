package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto,Integer> {

}
