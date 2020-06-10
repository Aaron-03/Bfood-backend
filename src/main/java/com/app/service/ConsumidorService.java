package com.app.service;

import org.springframework.stereotype.Service;

import com.app.common.Dao;
import com.app.entity.Consumidor;


@Service
public interface ConsumidorService extends Dao<Consumidor, Integer> {

}
