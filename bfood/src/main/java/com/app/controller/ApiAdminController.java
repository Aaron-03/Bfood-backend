package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Seller;
import com.app.entity.Ubication;
import com.app.service.SellerService;
import com.app.service.UbicationService;

@RestController
@RequestMapping("/bfood")
public class ApiAdminController {

	@Autowired
	private SellerService sellerservice;
	
	@Autowired
	private UbicationService ubicationservice;
	
	@GetMapping("/prueba")
	public String miPage() {		
		return "Hola Mundo";
	}
	
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public String registerSeller(@RequestBody Seller seller) {
		
		sellerservice.registerSeller(seller);
		
		return "Registro exitoso";
	}	
	
	@PostMapping(path = "/registerUbication", consumes = "application/json", produces = "application/json")
	public String registerUbication(@RequestBody Ubication ubication) {
		
		ubicationservice.registerUbication(ubication);
		
		return "Registro de la localización exitoso";
	}
}
