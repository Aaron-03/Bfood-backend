package com.app.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SucursalDTO;
import com.app.entity.Seller;
import com.app.entity.Sucursal;
import com.app.service.SellerService;
import com.app.service.SucursalService;
import com.app.util.GoogleMapsApi;
import com.app.util.Mensaje;

@RestController
@RequestMapping("/bfood/sucursal")
@CrossOrigin
public class ApiSucursalController {

	@Autowired
	private GoogleMapsApi mapsApi;

	@Autowired
	private SucursalService sucursalService;
	
	@Autowired
	private SellerService sellerservice;

	@GetMapping(path = "/listaSucursales", produces = "application/json")
	public List<Sucursal> listarSucursal() {
		return sucursalService.read();
	}

	Sucursal valSucursal(double lat, double lng) {

		List<Sucursal> lst = sucursalService.read();

		for (Sucursal s : lst) {
			if (s.getLat() == lat && s.getLng() == lng) {
				return s;
			}
		}

		return null;
	}

	@GetMapping(path = "/localizar/{latitud},{longitud}", produces = "application/json")
	public ResponseEntity<?> encontrarsucursal(@PathVariable("latitud") Double latitud,
			@PathVariable("longitud") Double longitud) throws Exception {

		Sucursal sucursal = valSucursal(latitud, longitud);

		if (sucursal == null) {
			return new ResponseEntity<>(":( no encontrado", HttpStatus.NOT_FOUND);
		} else {

			System.out.println(":) encontrado");
			return ok(mapsApi.getLocation(sucursal.getLat(), sucursal.getLng()));
		}

	}

	@PreAuthorize("hasRole('Vendedor')")
	@PostMapping(path = "/registrar-sucursal")
	public ResponseEntity<?> addSucursal(@Valid @RequestBody SucursalDTO dto) {
		try {
			if (StringUtils.isBlank(dto.getNombre()))
				return new ResponseEntity<>(new Mensaje(false, "ingrese el nombre del establecimineto"), HttpStatus.BAD_REQUEST);
			
			Sucursal sucursal =new Sucursal();
			Seller seller = sellerservice.get(dto.getSeller().getId());
			sucursal.setSeller(seller);
			sucursal.setNombre(dto.getNombre());
			sucursal.setDireccion(dto.getDireccion());
			sucursal.setLat(dto.getLat());
			sucursal.setLng(dto.getLng());
			sucursal.setEstado(dto.getEstado());
			
			sucursalService.save(sucursal);
			return new ResponseEntity<>(new Mensaje(true, "se registró nueva sucursal"), HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	@GetMapping(path = "/mysucursal/{negocioId}", produces = "application/json")
//	public List<Sucursal> listSucursal(@PathVariable("negocioId") int negocioId) {
//		Negocio negocio = negocioService.get(negocioId);
//		System.out.println("imprime:" + negocio);
//		List<Sucursal> lst = sucursalService.read().stream().filter(x -> x.getNegocio() == negocio)
//				.collect(Collectors.toList());
//
//		return lst;
//	}

}
