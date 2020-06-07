package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Pedido;


@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

}

