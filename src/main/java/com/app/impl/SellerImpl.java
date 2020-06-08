package com.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.common.DaoImpl;
import com.app.entity.Seller;
import com.app.repository.SellerRepository;
import com.app.service.SellerService;


@Service
public class SellerImpl extends DaoImpl<Seller, Integer> implements SellerService {
	
	@Autowired
	private SellerRepository sellerrepository;

	
	public void registerSeller(Seller seller) {
		
		sellerrepository.save(seller);
		
	}
	
	public List<Seller> viewSeller() {
		
		return sellerrepository.findAll();
	}


	@Override
	public CrudRepository<Seller, Integer> getDao() {
		return sellerrepository;
	}
}
