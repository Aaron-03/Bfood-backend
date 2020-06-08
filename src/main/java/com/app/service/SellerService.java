package com.app.service;


import org.springframework.stereotype.Service;

import com.app.common.Dao;
import com.app.entity.Seller;


@Service
public interface SellerService extends Dao<Seller, Integer> {

	
}
