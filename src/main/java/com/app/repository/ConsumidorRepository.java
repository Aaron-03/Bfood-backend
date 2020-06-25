package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Consumidor;



@Repository
public interface ConsumidorRepository extends CrudRepository<Consumidor, Integer> {

}
