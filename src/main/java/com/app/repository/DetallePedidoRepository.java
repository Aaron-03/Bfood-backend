package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.DetallePedido;

@Repository
public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {

}
