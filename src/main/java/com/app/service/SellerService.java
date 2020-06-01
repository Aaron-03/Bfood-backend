package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.Seller;
import com.app.repository.SellerRepository;

public class SellerService {

	@Autowired
	private SellerRepository sellerrepository;

	
	public void registerSeller(Seller seller) {
		
		sellerrepository.save(seller);
		
	}
	
	public List<Seller> viewSeller() {
		
		return sellerrepository.findAll();
	}
}
