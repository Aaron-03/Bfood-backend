package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.Ubication;
import com.app.repository.UbicationRepository;

public class UbicationService {

	@Autowired
	private UbicationRepository ubicationrepository;
	
	public void registerUbication(Ubication ubication) {
		ubicationrepository.save(ubication);
	}
	
	public List<Ubication> viewUbication(){
		return ubicationrepository.findAll();
	}
}
