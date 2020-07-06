package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Sucursal;

@Repository
public interface SucursalRepository extends CrudRepository<Sucursal, Integer> {

}
