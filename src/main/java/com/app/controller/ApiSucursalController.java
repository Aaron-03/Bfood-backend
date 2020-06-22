package com.app.controller;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Negocio;
import com.app.entity.Sucursal;
import com.app.service.NegocioService;
import com.app.service.SucursalService;
import com.app.util.GoogleMapsApi;


@RestController
@RequestMapping("/bfood")
@CrossOrigin
public class ApiSucursalController {

	@Autowired
	private GoogleMapsApi mapsApi;

	@Autowired
	private SucursalService sucursalService;



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
		
		if (sucursal==null) {
			return new ResponseEntity<>(":( no encontrado", HttpStatus.NOT_FOUND);
		} else {

			System.out.println(":) encontrado");
			return ok(mapsApi.getLocation(sucursal.getLat(), sucursal.getLng()));
		}

	}


	@Autowired
	private NegocioService negocioService;

	@PostMapping(path = "/register-sucursal", consumes = "application/json", produces = "application/json")
	public String addSucursal(@RequestBody Sucursal s) {
		Negocio negocio = negocioService.get(1);
		
		Sucursal newsuSucursal=new Sucursal(negocio,s.getDireccion(), s.getLat(),s.getLng(),s.getEstado());
		sucursalService.save(newsuSucursal);
		return ":) success!";
	}
	
	
	@GetMapping(path = "/mysucursal/{negocioId}", produces = "application/json")
	public List<Sucursal> listSucursal(@PathVariable("negocioId") int negocioId) {
		Negocio negocio = negocioService.get(negocioId);
		System.out.println("imprime:"+negocio);
		List<Sucursal> lst = sucursalService.read().stream().filter(x -> x.getNegocio() == negocio)
				.collect(Collectors.toList());

		return lst;
	}

}
